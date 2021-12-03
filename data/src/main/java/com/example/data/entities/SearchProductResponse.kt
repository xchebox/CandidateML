package com.example.data.entities

import com.example.data.entities.ProductEntity
import com.google.gson.annotations.SerializedName

data class SearchProductResponse (
    @SerializedName("site_id")
    val site_id: String?,
    @SerializedName("country_default_time_zone")
    val country_default_time_zone: String?,
    @SerializedName("query")
    val query: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("results")
    val results: List<ProductEntity> = emptyList()
)