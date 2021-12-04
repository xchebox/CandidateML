package com.example.data.db.repositories

import com.example.data.api.ApiService
import com.example.data.db.dao.ProductDBDao
import com.example.data.db.mapper.ProductDBMapper
import com.example.data.mappers.ProductMapper
import com.example.domain.entities.Product
import com.example.domain.repositories.ProductDBRepository
import com.example.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductDBRepositoryImpl @Inject constructor(
    private val productDao: ProductDBDao,
    private val productDBMapper : ProductDBMapper
): ProductDBRepository {
    override fun getProduct(productId: String): Product {
        return productDBMapper.toProduct(productDao.getProduct(productId))
    }

    override fun insertAll(products: List<Product>) {
        productDao.insertAll(productDBMapper.toProductDBList(products))
    }

}