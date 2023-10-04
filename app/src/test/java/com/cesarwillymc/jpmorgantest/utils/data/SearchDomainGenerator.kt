package com.cesarwillymc.jpmorgantest.utils.data

import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import com.cesarwillymc.jpmorgantest.utils.data.SearchDataGenerator.weatherData

object SearchDomainGenerator {
    val weatherDomain = WeatherDetail(
        cityName = weatherData.name,
        temperature = weatherData.main.feelsLike,
        weatherDescription = weatherData.weather.firstOrNull()?.description.orEmpty(),
        humidity = weatherData.main.humidity,
        pressure = weatherData.main.pressure,
        windSpeed = weatherData.wind.speed,
        sunrise = weatherData.sys.sunrise,
        sunset = weatherData.sys.sunset,
        icon = weatherData.weather.firstOrNull()?.icon
    )
}
