package com.cesarwillymc.jpmorgantest.data.sources.search.di

import com.cesarwillymc.jpmorgantest.data.sources.search.SearchDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.SearchRepository
import com.cesarwillymc.jpmorgantest.data.sources.search.mapper.SearchResultMapper
import com.cesarwillymc.jpmorgantest.data.sources.search.mapper.SearchResultMapperImpl
import com.cesarwillymc.jpmorgantest.data.sources.search.remote.SearchRemoteDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.remote.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
}
