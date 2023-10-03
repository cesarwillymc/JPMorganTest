package com.cesarwillymc.jpmorgantest.data.di

import android.content.Context
import android.content.SharedPreferences
import com.cesarwillymc.jpmorgantest.BuildConfig
import com.cesarwillymc.jpmorgantest.data.sources.local.EncryptedSharedPreferencesFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun getEncryptedSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return EncryptedSharedPreferencesFactory(
            BuildConfig.SHARED_PREFERENCES_NAME,
            appContext
        ).sharedPreferences
    }
}
