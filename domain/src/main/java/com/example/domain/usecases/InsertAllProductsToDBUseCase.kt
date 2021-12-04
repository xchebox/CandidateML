package com.example.domain.usecases

import com.example.domain.entities.Product
import com.example.domain.repositories.ProductDBRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class InsertAllProductsToDBUseCase @Inject constructor(private val productDBRepository: ProductDBRepository) :
    BaseUseCase<List<Product>, Boolean> {
    override suspend fun execute(params: List<Product>?): Boolean {
        return try {
            productDBRepository.insertAll(params!!)
            true
        } catch (t: Throwable) {
            false
        }

    }
}