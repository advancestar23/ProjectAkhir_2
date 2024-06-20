package com.vinz.projectcontoh1.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFood(appEntity: AppEntity)

    @Update
    fun updateFood(appEntity: AppEntity)

    @Delete
    fun deleteFood(appEntity: AppEntity)

    @Query("SELECT * FROM appentity ORDER BY id DESC")
    fun getAllFood() : LiveData<List<AppEntity>>
}