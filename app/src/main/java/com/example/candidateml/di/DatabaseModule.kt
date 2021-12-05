package com.example.candidateml.di

import android.content.Context
import androidx.room.Room
import com.example.cache.dao.ProductDBDao
import com.example.cache.database.ApplicationDatabase
import com.example.cache.repositories.ProductDBRepositoryImpl
import com.example.domain.repositories.ProductDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): com.example.cache.database.ApplicationDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            com.example.cache.database.ApplicationDatabase::class.java,
            "ApplicationDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(applicationDatabase: com.example.cache.database.ApplicationDatabase): com.example.cache.dao.ProductDBDao {
        return applicationDatabase.productDao()
    }

    @Provides
    @Singleton
    fun provideProductDBRepository(productDBRepositoryImpl: com.example.cache.repositories.ProductDBRepositoryImpl): ProductDBRepository =
        productDBRepositoryImpl

}
