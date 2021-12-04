package com.example.candidateml.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.Product
import com.example.domain.usecases.GetProductDetailsUseCase
import com.example.domain.usecases.GetProductFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val getProductFromDBUseCase: GetProductFromDBUseCase,
): ViewModel() {
    val product = MutableLiveData<Product>();

    fun getProductDetails(productId: String){
        getProductDetailsUseCase.execute(productId).map {
            product.postValue(it)
        }
    }

    fun getProductDetailsFromDB(productId: String){
        val productDetails = getProductFromDBUseCase.execute(productId)
        product.postValue(productDetails)
    }
}