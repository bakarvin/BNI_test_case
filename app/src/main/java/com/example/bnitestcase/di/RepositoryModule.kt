package com.example.bnitestcase.di

import com.example.bnitestcase.data.local.QrTransactionCache
import com.example.bnitestcase.data.remote.api.PromoApi
import com.example.bnitestcase.data.repository.PromoRepository
import com.example.bnitestcase.data.repository.PromoRepositoryImpl
import com.example.bnitestcase.data.repository.QrTransactionRepository
import com.example.bnitestcase.data.repository.QrTransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideQrTransactionRepository(
        cache: QrTransactionCache
    ): QrTransactionRepository = QrTransactionRepositoryImpl(cache)

    @Provides
    @Singleton
    internal fun providePromoRepository(
        api: PromoApi
    ): PromoRepository = PromoRepositoryImpl(api)
}