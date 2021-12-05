package com.example.data.db.mapper

import com.example.data.db.entities.ProductDBEntity
import com.example.domain.entities.Product
import javax.inject.Inject

class ProductDBMapper @Inject constructor() {
    fun toProduct(productDBEntity: ProductDBEntity): Product {
        return Product(
            productDBEntity.id ?: "-1",
            productDBEntity.siteId ?: "",
            productDBEntity.title ?: "",
            productDBEntity.thumbnail ?: "",
            productDBEntity.price ?: 0
        )
    }

    fun toProductList(productDBEntityList: List<ProductDBEntity>): List<Product> {
        return productDBEntityList.map {
            toProduct(it)
        }
    }

    fun toProductDBEntity(product: Product): ProductDBEntity {
        return ProductDBEntity(
            product.id ?: "-1",
            product.siteId ?: "",
            product.title ?: "",
            product.thumbnail ?: "",
            product.price ?: 0 //TODO validate what to do if price is null
        )
    }

    fun toProductDBList(productList: List<Product>): List<ProductDBEntity> {
        return productList.map {
            toProductDBEntity(it)
        }
    }

}