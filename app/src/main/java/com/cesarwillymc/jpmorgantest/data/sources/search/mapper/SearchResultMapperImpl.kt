package com.cesarwillymc.jpmorgantest.data.sources.search.mapper

import com.cesarwillymc.jpmorgantest.data.sources.search.entities.WeatherDetailResponse
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Coord
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Main
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Sys
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Weather
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.Wind
import javax.inject.Inject

class SearchResultMapperImpl @Inject constructor() : SearchResultMapper {
    override fun fromResponseToDomain(info: WeatherDetailResponse): WeatherDetail {
        return WeatherDetail(
            base = info.base,
            clouds = info.clouds.all,
            cod = info.cod,
            coord = Coord(info.coord.lat, info.coord.lon),
            dt = info.dt,
            id = info.id,
            main = Main(
                feelsLike = info.main.feelsLike,
                humidity = info.main.humidity,
                pressure = info.main.pressure,
                temp = info.main.temp,
                tempMax = info.main.tempMax,
                tempMin = info.main.tempMin
            ),
            name = info.name,
            sys = Sys(
                info.sys.country,
                info.sys.id,
                info.sys.sunrise,
                info.sys.sunset,
                info.sys.type
            ),
            timezone = info.timezone,
            visibility = info.visibility,
            weather = info.weather.map {
                Weather(
                    description = it.description,
                    icon = it.icon,
                    id = it.id,
                    main = it.main
                )
            },
            wind = Wind(
                deg = info.wind.deg,
                gust = info.wind.gust,
                speed = info.wind.speed
            )
        )
    }
}
