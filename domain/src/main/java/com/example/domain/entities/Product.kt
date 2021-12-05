package com.example.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: String,
    @SerialName("siteId")
    val siteId: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("price")
    val price: Double? = null,
    @SerialName("price_text")
    val priceText: String? = null
)

data class ProductList(
    val products: List<Product>? = emptyList(),
)
