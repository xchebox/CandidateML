package com.example.candidateml.di

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import com.example.data.api.ApiService;
import com.example.data.repositories.ApiServiceImpl
import com.example.domain.repositories.ProductRepository
import com.example.domain.usecases.GetProductSearchUseCase
import dagger.Module
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class )
object ApplicationModule {
    @Provides
    fun provideProductSearchUseCase(productRepository: ProductRepository): GetProductSearchUseCase {
        return GetProductSearchUseCase(productRepository)
    }
}