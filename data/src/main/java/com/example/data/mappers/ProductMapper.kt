package com.example.data.mappers

import com.example.data.entities.ProductEntity
import com.example.domain.entities.Product

import javax.inject.Inject


class ProductMapper @Inject constructor() {

    fun toProduct(product: ProductEntity): Product {
        val price = product.price ?: 0.0
        return Product(
            product.id,
            product.siteId ?: "",
            product.title ?: "",
            product.thumbnail ?: "",
            price,
            "$".plus(price.toString())
        )
    }

    fun toProductList(productEntityList: List<ProductEntity>): List<Product> {
        return productEntityList.map {
            toProduct(it)
        }
    }
}