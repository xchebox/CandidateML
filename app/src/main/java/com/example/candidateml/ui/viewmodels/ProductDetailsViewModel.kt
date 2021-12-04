package com.example.candidateml.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Product
import com.example.domain.usecases.GetProductDetailsUseCase
import com.example.domain.usecases.GetProductFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val getProductFromDBUseCase: GetProductFromDBUseCase,
): ViewModel() {
    val product = MutableLiveData<Product>();

    fun getProductDetails(productId: String){
        viewModelScope.launch {
            getProductDetailsUseCase.execute(productId).map {
                product.postValue(it)
            }
        }
    }

    fun getProductDetailsFromDB(productId: String){
        viewModelScope.launch {
            val productDetails = getProductFromDBUseCase.execute(productId)
            product.postValue(productDetails)
        }
    }
}