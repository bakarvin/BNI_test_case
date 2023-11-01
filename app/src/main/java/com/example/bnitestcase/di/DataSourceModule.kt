package com.example.bnitestcase.di

import com.example.bnitestcase.data.local.QrTransactionCache
import com.example.bnitestcase.data.local.QrTransactionCacheImpl
import com.example.bnitestcase.util.PreferencesManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    internal fun providerQrTransactionCache(
        gson: Gson,
        pref: PreferencesManager
    ): QrTransactionCache = QrTransactionCacheImpl(gson, pref)
}