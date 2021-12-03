package com.example.candidateml.di

import com.example.data.api.ApiService
import com.example.data.mappers.ProductMapper
import com.example.data.repositories.ApiServiceImpl
import com.example.domain.repositories.ProductRepository
import com.example.domain.usecases.GetProductSearchUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient (httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/MLA/") //TODO eusebio implement correct url
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addNetworkInterceptor(logging).build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideRepoImplementation(apiService: ApiService, productMapper: ProductMapper ): ApiServiceImpl {
        return ApiServiceImpl(apiService, productMapper)
    }

    @Provides
    @Singleton
    fun bindApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(productRepository: ApiServiceImpl): ProductRepository =
        productRepository
}