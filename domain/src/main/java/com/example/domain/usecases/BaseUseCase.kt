package com.example.domain.usecases

import io.reactivex.rxjava3.core.Single

interface BaseUseCase<in Parameter, out R> {
    suspend fun execute(params: Parameter?): R
}