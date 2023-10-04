package com.cesarwillymc.jpmorgantest.data.sources.search.di

import com.cesarwillymc.jpmorgantest.data.sources.search.SearchDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.SearchRepository
import com.cesarwillymc.jpmorgantest.data.sources.search.local.SearchLocalDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.local.SearchLocalDataSourceImpl
import com.cesarwillymc.jpmorgantest.data.sources.search.mapper.SearchResultMapper
import com.cesarwillymc.jpmorgantest.data.sources.search.mapper.SearchResultMapperImpl
import com.cesarwillymc.jpmorgantest.data.sources.search.remote.SearchRemoteDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.remote.SearchRemoteDataSourceImpl
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
abstract class SearchModule {

    @Singleton
    @Binds
    abstract fun bindsSearchDataSource(searchRepository: SearchRepository): SearchDataSource

    @Singleton
    @Binds
    abstract fun bindsSearchResultMapper(
        searchResultMapper: SearchResultMapperImpl
    ): SearchResultMapper

    @Singleton
    @Binds
    abstract fun bindsSearchRemoteDataSource(
        searchResultRemoteDataSource: SearchRemoteDataSourceImpl
    ): SearchRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsSearchLocalDataSource(
        searchLocalDataSource: SearchLocalDataSourceImpl
    ): SearchLocalDataSource
}
