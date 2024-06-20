package com.vinz.projectcontoh1.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Mendeklarasikan database dengan entitas PlayerEntity dan versi 1
@Database(entities = [AppEntity::class, KategoriEntity::class], version = 2)

// Membuat kelas abstrak AppDatabase yang merupakan turunan dari AppDatabase
abstract class AppDatabase : RoomDatabase() {

    // Mendeklarasikan fungsi abstrak yang mengembalikan AppDao
    abstract fun appDao(): AppDao

    // Mendeklarasikan fungsi abstrak yang mengembalikan KategoriDao
    abstract fun kategoriDao(): KategoriDao

    // Membuat objek pendamping untuk AppDatabase
    companion object {
        // Mendeklarasikan variabel INSTANCE yang akan menyimpan instance dari AppDatabase
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Membuat fungsi statis untuk mendapatkan instance database
        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            // Jika INSTANCE null, maka akan dibuat instance baru
            if (INSTANCE == null) {
                // Menggunakan synchronized untuk mencegah akses bersamaan dari beberapa thread
                synchronized(AppDatabase::class.java) {
                    // Membuat instance baru dari AppDatabase
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "food_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            // Mengembalikan instance dari AppDatabase
            return INSTANCE as AppDatabase
        }
    }
}