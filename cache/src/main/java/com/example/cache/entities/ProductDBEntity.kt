package com.example.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductDBEntity
    (
    @PrimaryKey
    val id: String,
    val siteId: String,
    val title: String,
    val thumbnail: String,
    val price: Double,
    val priceText: String,
)
