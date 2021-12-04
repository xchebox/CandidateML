package com.example.domain.repositories

import com.example.domain.entities.Product
import io.reactivex.rxjava3.core.Single

interface ProductDBRepository {
    suspend fun getProduct(productId: String): Product
    suspend fun insertAll(products: List<Product>)
}