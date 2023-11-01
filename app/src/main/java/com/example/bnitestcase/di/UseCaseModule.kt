package com.example.bnitestcase.di

import com.example.bnitestcase.data.repository.PromoRepository
import com.example.bnitestcase.data.repository.QrTransactionRepository
import com.example.bnitestcase.domain.get.GetPromoUseCase
import com.example.bnitestcase.domain.get.GetPromoUseCaseImpl
import com.example.bnitestcase.domain.load.LoadBalanceUseCase
import com.example.bnitestcase.domain.load.LoadBalanceUseCaseImpl
import com.example.bnitestcase.domain.load.LoadHistoryUseCase
import com.example.bnitestcase.domain.load.LoadHistoryUseCaseImpl
import com.example.bnitestcase.domain.save.SaveBalanceUseCase
import com.example.bnitestcase.domain.save.SaveBalanceUseCaseImpl
import com.example.bnitestcase.domain.save.SaveHistoryUseCase
import com.example.bnitestcase.domain.save.SaveHistoryUseCaseImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    internal fun provideLoadBalanceUseCase(
        repository: QrTransactionRepository
    ) : LoadBalanceUseCase = LoadBalanceUseCaseImpl(repository)

    @Provides
    @Singleton
    internal fun provideLoadHistoryUseCase(
        repository: QrTransactionRepository
    ) : LoadHistoryUseCase = LoadHistoryUseCaseImpl(repository)

    @Provides
    @Singleton
    internal fun provideSaveBalanceUseCase(
        repository: QrTransactionRepository
    ) : SaveBalanceUseCase = SaveBalanceUseCaseImpl(repository)

    @Provides
    @Singleton
    internal fun provideSaveHistoryUseCase(
        repository: QrTransactionRepository
    ) : SaveHistoryUseCase = SaveHistoryUseCaseImpl(repository)

    @Provides
    @Singleton
    internal fun provideGetPromoUseCase(
        gson: Gson,
        repository: PromoRepository
    ) : GetPromoUseCase = GetPromoUseCaseImpl(gson, repository)
}