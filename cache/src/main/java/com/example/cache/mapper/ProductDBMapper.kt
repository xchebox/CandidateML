package com.example.cache.mapper

import com.example.domain.entities.Product
import javax.inject.Inject

class ProductDBMapper @Inject constructor() {
    fun toProduct(productDBEntity: com.example.cache.entities.ProductDBEntity): Product {
        val price = productDBEntity.price ?: 0.0
        return Product(
            productDBEntity.id ?: "-1",
            productDBEntity.siteId ?: "",
            productDBEntity.title ?: "",
            productDBEntity.thumbnail ?: "",
            price,
            "$".plus(price.toString())
        )
    }

    fun toProductList(productDBEntityList: List<com.example.cache.entities.ProductDBEntity>): List<Product> {
        return productDBEntityList.map {
            toProduct(it)
        }
    }

    fun toProductDBEntity(product: Product): com.example.cache.entities.ProductDBEntity {
        val price = product.price ?: 0.0
        return com.example.cache.entities.ProductDBEntity(
            product.id,
            product.siteId ?: "",
            product.title ?: "",
            product.thumbnail ?: "",
            price,
            "$".plus(price.toString())
        )
    }

    fun toProductDBList(productList: List<Product>): List<com.example.cache.entities.ProductDBEntity> {
        return productList.map {
            toProductDBEntity(it)
        }
    }

}