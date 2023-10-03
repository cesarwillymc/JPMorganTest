package com.cesarwillymc.jpmorgantest.data.sources.search.remote

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import com.cesarwillymc.jpmorgantest.util.state.Result
interface SearchRemoteDataSource {

    suspend fun search(
        query: String
    ): Result<WeatherDetailResponse>
}
