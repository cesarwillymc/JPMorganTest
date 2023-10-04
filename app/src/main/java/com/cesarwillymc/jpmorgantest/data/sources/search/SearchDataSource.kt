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
    suspend fun searchFilter(
        city: String,
        stateCode: String,
        countryCode: String
    ): Result<WeatherDetail>

    fun recentlySearched(): Result<String>
    fun saveQuery(
        city: String,
        stateCode: String,
        countryCode: String
    ): Result<Unit>
}
