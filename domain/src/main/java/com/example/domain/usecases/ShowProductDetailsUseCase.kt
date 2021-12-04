package com.example.domain.usecases

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ShowProductDetailsUseCase @Inject constructor() :
BaseUseCase<String, Unit> {
    override suspend fun execute(params: String?): Unit {
        print (params)
    }

}