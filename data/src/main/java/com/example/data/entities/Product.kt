package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    @SerializedName("id")
    val id: String = "-1",
    @SerializedName("site_id")
    val siteId: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null
)