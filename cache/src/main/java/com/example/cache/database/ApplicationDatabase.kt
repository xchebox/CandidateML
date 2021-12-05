package com.example.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [com.example.cache.entities.ProductDBEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun productDao(): com.example.cache.dao.ProductDBDao
}
