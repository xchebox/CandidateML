package com.example.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductDBEntity
    (
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "site_id")
    val siteId: String,
    val title: String,
    val thumbnail: String
)
