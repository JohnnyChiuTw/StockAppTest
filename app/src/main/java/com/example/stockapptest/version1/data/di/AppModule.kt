package com.example.stockapptest.version1.data.di

import com.example.stockapptest.version1.common.Constants
import com.example.stockapptest.version1.data.remote.StockApi
import com.example.stockapptest.version1.data.repository.StockRepositoryImpl
import com.example.stockapptest.version1.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi() : StockApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockRepository(api: StockApi): StockRepository {
        return StockRepositoryImpl(api)
    }
}