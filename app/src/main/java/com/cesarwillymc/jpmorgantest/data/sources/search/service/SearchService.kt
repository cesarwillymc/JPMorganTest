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
        @Query(QUERY_KEY) query: String?,
        @Query(LAT_KEY) lat: Double?,
        @Query(LON_KEY) lon: Double?,
        @Query(UNITS_KEY) units: String = UNITS_VALUE
    ): WeatherDetailResponse

    private companion object {
        const val QUERY_KEY = "q"
        const val LAT_KEY = "lat"
        const val LON_KEY = "lon"
        const val UNITS_KEY = "units"
        const val UNITS_VALUE = "metric"
        const val SEARCH = "data/2.5/weather"
    }
}
