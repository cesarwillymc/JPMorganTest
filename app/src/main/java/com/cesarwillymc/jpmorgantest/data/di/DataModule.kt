package com.cesarwillymc.jpmorgantest.data.di

import android.content.Context
import androidx.room.Room
import com.cesarwillymc.jpmorgantest.BuildConfig
import com.cesarwillymc.jpmorgantest.data.sources.db.JPMorganRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): JPMorganRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            JPMorganRoomDatabase::class.java,
            BuildConfig.NAME_DB
        ).build()
    }

}
