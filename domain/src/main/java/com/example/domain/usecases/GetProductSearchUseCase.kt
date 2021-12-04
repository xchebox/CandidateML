package com.example.domain.usecases

import com.example.domain.entities.Product
import com.example.domain.repositories.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetProductSearchUseCase @Inject constructor(private val productRepository: ProductRepository) :
    BaseUseCase<Unit, Single<List<Product>>> {
    override suspend fun execute(params: Unit?): Single<List<Product>> {
        return productRepository.getProductSearch();
    }

}