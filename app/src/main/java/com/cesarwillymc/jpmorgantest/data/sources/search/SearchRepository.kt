package com.cesarwillymc.jpmorgantest.data.sources.search

import com.cesarwillymc.jpmorgantest.data.sources.search.local.SearchLocalDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.mapper.SearchResultMapper
import com.cesarwillymc.jpmorgantest.data.sources.search.remote.SearchRemoteDataSource
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import com.cesarwillymc.jpmorgantest.util.extension.formatCityStateCountry
import com.cesarwillymc.jpmorgantest.util.state.Result
import com.cesarwillymc.jpmorgantest.util.state.map
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource,
    private val searchResultMapper: SearchResultMapper,
    private val searchLocalDataSource: SearchLocalDataSource
) : SearchDataSource {
    override suspend fun searchFilter(
        city: String,
        stateCode: String,
        countryCode: String
    ): Result<WeatherDetail> =
        remoteDataSource.search(formatCityStateCountry(city, stateCode, countryCode))
            .map(searchResultMapper::fromResponseToDomain)
    override fun recentlySearched(): Result<String> {
        return searchLocalDataSource.recentlySearched()
    }

    override fun saveQuery(
        city: String,
        stateCode: String,
        countryCode: String
    ): Result<Unit> {
        return searchLocalDataSource.saveQuery(formatCityStateCountry(city, stateCode, countryCode))
    }
}
