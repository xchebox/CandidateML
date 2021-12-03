package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class Paging (
    @SerializedName("total")
    val total: String = "0",
    @SerializedName("primary_results")
    val primary_results: String = "0",
    @SerializedName("offset")
    val offset: String = "0",
    @SerializedName("limit")
    val limit: String = "0"
)