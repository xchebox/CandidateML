package com.example.data.mappers

import com.example.data.entities.ProductEntity
import com.example.data.entities.SearchProductResponse
import com.example.domain.entities.Product

import javax.inject.Inject


/**
 * A mapper to map the ShareDataModel from server to ShareDetailsModel in a presentable form.
 */
class ProductMapper @Inject constructor() {

    fun toProduct(product: ProductEntity): Product {
        return Product(
            product.id ?: "-1",
            product.siteId ?: "",
            product.title ?: "",
            product.thumbnail ?: ""
        )
    }

    fun toProductList(productEntityList: List<ProductEntity>): List<Product> {
        return productEntityList.map {
            toProduct(it)
        }
    }
}