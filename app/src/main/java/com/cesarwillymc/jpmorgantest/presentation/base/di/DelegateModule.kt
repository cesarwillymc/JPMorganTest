package com.cesarwillymc.jpmorgantest.presentation.base.di

import com.cesarwillymc.jpmorgantest.presentation.base.delegate.LocationDelegate
import com.cesarwillymc.jpmorgantest.presentation.base.delegate.LocationDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DelegateModule {
    @Singleton
    @Binds
    abstract fun bindLocationDelegate(
        locationDelegateImpl: LocationDelegateImpl
    ): LocationDelegate
}
