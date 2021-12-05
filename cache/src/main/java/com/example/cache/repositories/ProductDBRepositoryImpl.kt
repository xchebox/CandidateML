package com.example.cache.repositories

import com.example.domain.entities.Product
import com.example.domain.repositories.ProductDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductDBRepositoryImpl @Inject constructor(
    private val productDao: com.example.cache.dao.ProductDBDao,
    private val productDBMapper : com.example.cache.mapper.ProductDBMapper
): ProductDBRepository {
    override suspend fun getProduct(productId: String): Product {
        return withContext(Dispatchers.IO){
            productDBMapper.toProduct(productDao.getProduct(productId))
        }
    }

    override suspend fun insertAll(products: List<Product>) {
        return withContext(Dispatchers.IO){
            productDao.insertAll(productDBMapper.toProductDBList(products))
        }
    }

}