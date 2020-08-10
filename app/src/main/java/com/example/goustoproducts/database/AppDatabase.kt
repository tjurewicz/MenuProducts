package com.example.goustoproducts.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DBProductData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): IProductDataDao
}