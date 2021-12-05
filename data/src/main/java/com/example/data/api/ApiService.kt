package com.example.data.api

import com.example.data.entities.SearchProductResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("search")
    fun searchProduct(@Query("q") query: String): Single<SearchProductResponse>

    /*
    TODO Response is not the correct one but could not be retrieved anyways since i don't have a token
     */
    @GET("https://api.mercadolibre.com/sites/MLA/items")
    fun getProduct(@Query("ids") productId: String): Single<SearchProductResponse>

}