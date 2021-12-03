package com.example.domain.usecases

import com.example.domain.entities.Product
import com.example.domain.repositories.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(private val productRepository: ProductRepository) :
    BaseUseCase<String, Single<Product>> {
    override fun execute(params: String?): Single<Product> {
        return productRepository.getProductDetails(params!!);
    }

}