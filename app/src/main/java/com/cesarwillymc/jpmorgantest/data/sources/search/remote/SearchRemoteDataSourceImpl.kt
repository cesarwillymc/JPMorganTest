package com.cesarwillymc.jpmorgantest.data.sources.search.remote

import com.cesarwillymc.jpmorgantest.data.sources.network.util.BaseRemoteDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import com.cesarwillymc.jpmorgantest.data.sources.search.service.SearchService
import com.cesarwillymc.jpmorgantest.util.state.Result
import javax.inject.Inject

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRemoteDataSource,
    BaseRemoteDataSource() {
    override suspend fun search(
        query: String?,
        lat: Double?,
        lon: Double?
    ): Result<WeatherDetailResponse> = getResult {
        searchService.search(query, lat, lon)
    }
}
