package com.example.candidateml.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.dao.ProductDBDao
import com.example.candidateml.database.ApplicationDatabase
import com.example.data.db.repositories.ProductDBRepositoryImpl
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
    fun provideProductDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ApplicationDatabase::class.java,
            "ApplicationDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(applicationDatabase: ApplicationDatabase): ProductDBDao {
        return applicationDatabase.productDao()
    }

    @Provides
    @Singleton
    fun provideProductDBRepository(productDBRepositoryImpl: ProductDBRepositoryImpl): ProductDBRepository =
        productDBRepositoryImpl

}
