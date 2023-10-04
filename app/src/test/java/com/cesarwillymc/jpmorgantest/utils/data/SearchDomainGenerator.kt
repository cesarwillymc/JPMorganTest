package com.cesarwillymc.jpmorgantest.utils.data

import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import com.cesarwillymc.jpmorgantest.utils.data.SearchDataGenerator.weatherData

object SearchDomainGenerator {
    val weatherDomain = WeatherDetail(
        base = weatherData.base,
        clouds = weatherData.clouds.all,
        cod = weatherData.cod,
        coord = Coord(
            lat = weatherData.coord.lat,
            lon = weatherData.coord.lon
        ),
        dt = weatherData.dt,
        id = weatherData.id,
        main = Main(
            feelsLike = weatherData.main.feelsLike,
            humidity = weatherData.main.humidity,
            pressure = weatherData.main.pressure,
            temp = weatherData.main.temp,
            tempMax = weatherData.main.tempMax,
            tempMin = weatherData.main.tempMin
        ),
        name = weatherData.name,
        sys = Sys(
            country = weatherData.sys.country,
            id = weatherData.sys.id,
            sunrise = weatherData.sys.sunrise,
            sunset = weatherData.sys.sunset,
            type = weatherData.sys.type
        ),
        timezone = weatherData.timezone,
        visibility = weatherData.visibility,
        weather = listOf(
            Weather(
                description = weatherData.weather[0].description,
                icon = weatherData.weather[0].icon,
                id = weatherData.weather[0].id,
                main = weatherData.weather[0].main
            )
        ),
        wind = Wind(
            deg = weatherData.wind.deg,
            gust = weatherData.wind.gust,
            speed = weatherData.wind.speed
        )
    )
}
