package com.example.domain.repositories

import com.example.domain.entities.Product
import io.reactivex.rxjava3.core.Single

interface ProductDBRepository {
    fun getProduct(productId: String): Product
    fun insertAll(products: List<Product>)
}