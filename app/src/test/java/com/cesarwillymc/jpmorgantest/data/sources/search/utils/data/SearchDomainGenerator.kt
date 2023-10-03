package com.cesarwillymc.jpmorgantest.data.sources.search.utils.data

import com.cesarwillymc.jpmorgantest.data.sources.search.utils.data.SearchDataGenerator.weatherData
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Coord
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Main
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Sys
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Weather
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Wind

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
