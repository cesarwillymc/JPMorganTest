package com.cesarwillymc.jpmorgantest.data.sources.search.service

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET(SEARCH)
    suspend fun search(
        @Query(QUERY_KEY) query: String
    ): WeatherDetailResponse

    private companion object {
        const val QUERY_KEY = "q"
        const val SEARCH = "data/2.5/weather"
    }
}
