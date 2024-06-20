package com.vinz.projectcontoh1.utils

import android.content.Context
import com.vinz.projectcontoh1.data.AppRepository
import com.vinz.projectcontoh1.data.room.AppDatabase

object DependencyInjection {

    fun provideRepository(context: Context) : AppRepository {
        val database = AppDatabase.getDatabase(context)
        val appExecutors = AppExecutors()
        val appDao = database.appDao()
        val kategoriDao = database.kategoriDao()
        return AppRepository.getInstance(appDao, kategoriDao, appExecutors)
    }

}