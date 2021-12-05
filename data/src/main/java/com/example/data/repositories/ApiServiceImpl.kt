package com.example.data.repositories

import android.util.Log
import com.example.data.api.ApiService
import com.example.data.mappers.ProductMapper
import com.example.domain.entities.Product
import com.example.domain.repositories.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val apiService: ApiService,
    private val productMapper : ProductMapper
): ProductRepository {
    override fun getProductSearch(query: String): Single<List<Product>> {
        return apiService.searchProduct(query).map {
            productMapper.toProductList(it.results)
        }
    }

    override fun getProductDetails(productId: String): Single<Product> {
        return apiService.getProduct(productId).map {
            productMapper.toProduct(it.results[0])
        }
    }
}
