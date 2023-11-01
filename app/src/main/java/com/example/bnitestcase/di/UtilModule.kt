package com.example.bnitestcase.di

import android.content.Context
import com.example.bnitestcase.util.PreferencesManager
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {
    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().disableHtmlEscaping().create()

    @Provides
    @Singleton
    fun providePrefManager(
        @ApplicationContext context: Context
    ) = PreferencesManager(context)
}