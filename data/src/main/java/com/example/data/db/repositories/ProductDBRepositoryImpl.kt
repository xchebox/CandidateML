package com.example.data.db.repositories

import com.example.data.api.ApiService
import com.example.data.db.dao.ProductDBDao
import com.example.data.db.mapper.ProductDBMapper
import com.example.data.mappers.ProductMapper
import com.example.domain.entities.Product
import com.example.domain.repositories.ProductDBRepository
import com.example.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductDBRepositoryImpl @Inject constructor(
    private val productDao: ProductDBDao,
    private val productDBMapper : ProductDBMapper
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