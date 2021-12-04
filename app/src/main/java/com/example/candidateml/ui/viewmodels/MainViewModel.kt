package com.example.candidateml.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.domain.usecases.GetProductSearchUseCase
import com.example.domain.usecases.InsertAllProductsToDBUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductSearchUseCase: GetProductSearchUseCase,
    private val insertAllProductsToDBUseCase: InsertAllProductsToDBUseCase,
) : ViewModel() {
    val productList = MutableLiveData<List<Product>>();

    fun getProductResult() {
        getProductSearchUseCase.execute(null).subscribeOn(Schedulers.io())
            .subscribe({
                productList.postValue(it)
                insertAllProductsToDBUseCase.execute(it)
            }, {})
    }
}