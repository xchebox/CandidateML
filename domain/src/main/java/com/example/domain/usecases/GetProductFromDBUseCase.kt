package com.example.domain.usecases

import com.example.domain.entities.Product
import com.example.domain.repositories.ProductDBRepository
import javax.inject.Inject

class GetProductFromDBUseCase @Inject constructor(private val productDBRepository: ProductDBRepository) :
    BaseUseCase<String, Product> {
    override suspend fun execute(params: String?): Product {
        return productDBRepository.getProduct(params!!)
    }
}