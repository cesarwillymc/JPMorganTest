package com.cesarwillymc.jpmorgantest.data.sources.search.entities

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
data class WeatherDetailResponse(
    val base: String,
    val clouds: CloudsResponse,
    val cod: Int,
    val coord: CoordResponse,
    val dt: Int,
    val id: Int,
    val main: MainResponse,
    val name: String,
    val sys: SysResponse,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherResponse>,
    val wind: WindResponse
)
