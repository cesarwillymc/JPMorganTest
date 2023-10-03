package com.cesarwillymc.jpmorgantest.domain.usecase.entities

data class WeatherDetail(
    val base: String,
    val clouds: Int,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
