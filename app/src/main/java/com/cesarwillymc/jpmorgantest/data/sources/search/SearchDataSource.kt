package com.cesarwillymc.jpmorgantest.data.sources.search

import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import com.cesarwillymc.jpmorgantest.util.state.Result

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
interface SearchDataSource {
    suspend fun searchByQuery(
        query: String
    ): Result<WeatherDetail>

    suspend fun searchByLatLon(
        lat: Double,
        lon: Double
    ): Result<WeatherDetail>

    fun recentlySearched(): Result<String>
    fun saveQuery(
        query: String
    ): Result<Unit>
}
