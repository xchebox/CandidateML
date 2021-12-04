package com.example.candidateml.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.ProductDBDao
import com.example.data.db.entities.ProductDBEntity

@Database(entities = [ProductDBEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDBDao
}
