package com.example.candidateml.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.domain.usecases.GetProductSearchUseCase
import com.example.domain.usecases.InsertAllProductsToDBUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductSearchUseCase: GetProductSearchUseCase,
    private val insertAllProductsToDBUseCase: InsertAllProductsToDBUseCase,
) : ViewModel() {
    val productList = MutableLiveData<List<Product>>();

    fun getProductResult() {
        viewModelScope.launch {
            getProductSearchUseCase.execute(null).subscribeOn(Schedulers.io())
                .subscribe({
                    productList.postValue(it)
                    addAllToDB(it)
                }, {})
        }
    }

    private fun addAllToDB(products: List<Product>){
        viewModelScope.launch {
            insertAllProductsToDBUseCase.execute(products)
        }
    }
}