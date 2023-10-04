package com.cesarwillymc.jpmorgantest.data.sources.search.service

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
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
