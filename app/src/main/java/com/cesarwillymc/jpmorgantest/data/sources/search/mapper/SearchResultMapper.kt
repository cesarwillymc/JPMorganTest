package com.cesarwillymc.jpmorgantest.data.sources.search.mapper

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail

interface SearchResultMapper {
    fun fromResponseToDomain(info: WeatherDetailResponse): WeatherDetail
}
