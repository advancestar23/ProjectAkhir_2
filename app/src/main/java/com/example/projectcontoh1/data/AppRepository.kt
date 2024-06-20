package com.vinz.projectcontoh1.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vinz.projectcontoh1.data.retrofit.APIConfig
import com.vinz.projectcontoh1.data.retrofit.KategoriResponse
import com.vinz.projectcontoh1.data.room.AppDao
import com.vinz.projectcontoh1.data.room.AppEntity
import com.vinz.projectcontoh1.data.room.KategoriDao
import com.vinz.projectcontoh1.utils.AppExecutors
import com.vinz.projectcontoh1.utils.toListKategoriEntity
import com.vinz.projectcontoh1.utils.toListKategoriResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository private constructor(
    private val appDao: AppDao,
    private val kategoriDao: KategoriDao,
    private val appExecutors: AppExecutors
) {

    private val _listFood = MutableLiveData<List<KategoriResponse>>()
    var listFood: LiveData<List<KategoriResponse>> = _listFood

    fun getAllKategori() {
        val service = APIConfig.getApiService().getAllKategori()
        service.enqueue(object : Callback<List<KategoriResponse>> {
            override fun onResponse(
                call: Call<List<KategoriResponse>>,
                response: Response<List<KategoriResponse>>
            ) {

                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _listFood.value = response.body()

                    appExecutors.diskIO().execute {
                        kategoriDao.insertAllKategori(responseBody.toListKategoriEntity())
                    }
                } else {
                    Log.e("Error on Response", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<KategoriResponse>>, t: Throwable) {
                Log.e("Error on Failure", "onFailure: ${t.message}")
                appExecutors.diskIO().execute {
                    _listFood.postValue(kategoriDao.getAllKategoriList().toListKategoriResponse())
                }
            }
        })
    }

    fun insertFood(appEntity: AppEntity) {
        appExecutors.diskIO().execute { appDao.insertFood(appEntity) }
    }

    fun updateFood(appEntity: AppEntity) {
        appExecutors.diskIO().execute { appDao.updateFood(appEntity) }
    }

    fun deleteFood(appEntity: AppEntity) {
        appExecutors.diskIO().execute { appDao.deleteFood(appEntity) }
    }

    fun getAllFood(): LiveData<List<AppEntity>> = appDao.getAllFood()

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(
            appDao: AppDao,
            kategoriDao: KategoriDao,
            appExecutors: AppExecutors
        ): AppRepository =
        // Jika instance sudah ada, kembalikan instance tersebut.
            // Jika instance belum ada, buat instance baru.
            instance ?: synchronized(this) {
                instance ?: AppRepository(appDao, kategoriDao, appExecutors)
            }.also { instance = it }
    }
}