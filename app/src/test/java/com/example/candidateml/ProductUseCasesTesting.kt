package com.example.candidateml

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.candidateml.ui.viewmodels.ProductDetailsViewModel
import com.example.candidateml.ui.viewmodels.SearchProductViewModel
import com.example.domain.entities.Product
import com.example.domain.repositories.ProductDBRepository
import com.example.domain.repositories.ProductRepository
import com.example.domain.usecases.GetProductDetailsUseCase
import com.example.domain.usecases.GetProductFromDBUseCase
import com.example.domain.usecases.GetProductSearchUseCase
import com.example.domain.usecases.InsertAllProductsToDBUseCase
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class ProductUseCasesTesting {
    @Rule
    @JvmField

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    //mocks
    val productRepository = mock(ProductRepository::class.java)
    val productDBRepository = mock(ProductDBRepository::class.java)
    val productMock = Product("1", "MLA", "title", "thumbnail", 127.01, "$127.01")
    val productListMock = listOf(productMock, productMock, productMock, productMock)

    //use cases
    val productSearchUseCase by lazy { GetProductSearchUseCase(productRepository) }
    val getProductDetailsUseCase by lazy { GetProductDetailsUseCase(productRepository) }
    val insertAllProductsToDBUseCase by lazy { InsertAllProductsToDBUseCase(productDBRepository) }
    val getProductFromDBUseCase by lazy { GetProductFromDBUseCase(productDBRepository) }

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @ExperimentalCoroutinesApi
    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    /**
     * Should return the product list correctly
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetProductSearchUseCase() = runTest {
        val viewModel = SearchProductViewModel(productSearchUseCase, insertAllProductsToDBUseCase)
        val observer = Observer<List<Product>> {}

        `when`(productRepository.getProductSearch(anyString())).thenReturn(
            Single.just(
                productListMock
            )
        )

        try{
            viewModel.productList.observeForever(observer)
            viewModel.getProductResult("mock")
            val value = viewModel.productList.getOrAwaitValue()
            assertEquals(productListMock, value)
        } finally {
            viewModel.productList.removeObserver(observer)
        }
    }


    /**
     * Should return the product correctly
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetProductDetailsUseCase() = runTest {
        val viewModel = ProductDetailsViewModel(getProductDetailsUseCase, getProductFromDBUseCase)
        val observer = Observer<Product> {}

        `when`(productDBRepository.getProduct("mock"))
            .thenReturn(productMock)

        try{
            viewModel.product.observeForever(observer)
            viewModel.getProductDetailsFromDB("mock")
            val value = viewModel.product.getOrAwaitValue()
            assertEquals(productMock, value)
        } finally {
            viewModel.product.removeObserver(observer)
        }
    }


}