package com.example.domain.repositories

import com.example.domain.entities.Product
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProductSearch(): Single<List<Product>>
    fun getProductDetails(productOd: String): Single<Product>
}