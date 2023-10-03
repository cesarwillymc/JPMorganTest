package com.cesarwillymc.jpmorgantest.data.sources.search.mapper

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
interface SearchResultMapper {
    fun fromResponseToDomain(info: WeatherDetailResponse): WeatherDetail
}
