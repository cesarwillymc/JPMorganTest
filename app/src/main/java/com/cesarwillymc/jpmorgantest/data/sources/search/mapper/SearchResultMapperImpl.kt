package com.cesarwillymc.jpmorgantest.data.sources.search.mapper

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import javax.inject.Inject

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class SearchResultMapperImpl @Inject constructor() : SearchResultMapper {
    override fun fromResponseToDomain(info: WeatherDetailResponse): WeatherDetail {
        return WeatherDetail(
            cityName = info.name,
            temperature = info.main.feelsLike,
            weatherDescription = info.weather.firstOrNull()?.description.orEmpty(),
            humidity = info.main.humidity,
            pressure = info.main.pressure,
            windSpeed = info.wind.speed,
            sunrise = info.sys.sunrise,
            sunset = info.sys.sunset,
            icon = info.weather.firstOrNull()?.icon
        )
    }
}
