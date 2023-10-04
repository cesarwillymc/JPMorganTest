package com.cesarwillymc.jpmorgantest.data.sources.search.remote

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import com.cesarwillymc.jpmorgantest.util.state.Result

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
interface SearchRemoteDataSource {
    suspend fun search(
        query: String? = null,
        lat: Double? = null,
        lon: Double? = null
    ): Result<WeatherDetailResponse>
}
