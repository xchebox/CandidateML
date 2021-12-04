package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entities.ProductDBEntity

@Dao
interface ProductDBDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): List<ProductDBEntity>

    @Query("SELECT * FROM products WHERE  id = :id")
    fun getProduct(id: String): ProductDBEntity

    @Query("DELETE FROM products")
    fun clearProducts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductDBEntity>)
}