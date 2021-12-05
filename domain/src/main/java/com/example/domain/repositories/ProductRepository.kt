package com.example.domain.repositories

import com.example.domain.entities.Product
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProductSearch(query: String): Single<List<Product>>
    fun getProductDetails(productId: String): Single<Product>
}