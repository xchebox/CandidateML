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
            productDBEntity.thumbnail ?: ""
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
            product.thumbnail ?: ""
        )
    }

    fun toProductDBList(productList: List<Product>): List<ProductDBEntity> {
        return productList.map {
            toProductDBEntity(it)
        }
    }

}