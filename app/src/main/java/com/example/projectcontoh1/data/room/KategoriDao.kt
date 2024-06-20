package com.vinz.projectcontoh1.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KategoriDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKategori(data: List<KategoriEntity>)

    @Query("DELETE FROM kategori")
    fun deleteAllKategori()

    @Query("SELECT * FROM KATEGORI ORDER BY id DESC")
    fun getAllKategori() : LiveData<List<KategoriEntity>>

    @Query("SELECT * FROM KATEGORI ORDER BY id DESC")
    fun getAllKategoriList() : List<KategoriEntity>
}