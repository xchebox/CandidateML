package com.example.candidateml.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Product
import com.example.domain.usecases.GetProductSearchUseCase
import com.example.domain.usecases.InsertAllProductsToDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val getProductSearchUseCase: GetProductSearchUseCase,
    private val insertAllProductsToDBUseCase: InsertAllProductsToDBUseCase,
) : ViewModel() {
    private val TAG = "SearchProductViewModel"
    val mLoading = MutableLiveData(false)
    val productList = MutableLiveData<List<Product>>()


    fun getProductResult(query: String) {
        mLoading.postValue(true)
        viewModelScope.launch {
            getProductSearchUseCase.execute(query).subscribeOn(Schedulers.io())
                .subscribe({
                    productList.postValue(it)
                    addAllToDB(it)
                    mLoading.postValue(false);
                }, {
                    it.message?.let { it1 -> Log.e(TAG, it1) }
                    mLoading.postValue(false)
                })
        }
    }

    private fun addAllToDB(products: List<Product>){
        viewModelScope.launch {
            insertAllProductsToDBUseCase.execute(products)
        }
    }
}