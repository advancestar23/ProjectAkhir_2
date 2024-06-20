package com.vinz.projectcontoh1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vinz.projectcontoh1.data.AppRepository
import com.vinz.projectcontoh1.data.retrofit.KategoriResponse
import com.vinz.projectcontoh1.data.room.AppEntity

class AppViewModel(private val appRepository: AppRepository) : ViewModel() {

    val listFood: LiveData<List<KategoriResponse>> = appRepository.listFood

    fun getAllKategori() {
        appRepository.getAllKategori()
    }

    fun insertFood(appEntity: AppEntity) {
        appRepository.insertFood(appEntity)
    }

    fun getAllFood(): LiveData<List<AppEntity>> {
        return appRepository.getAllFood()
    }

    fun updateFood(appEntity: AppEntity) {
        appRepository.updateFood(appEntity)
    }

    fun deleteFood(appEntity: AppEntity) {
        appRepository.deleteFood(appEntity)
    }

}