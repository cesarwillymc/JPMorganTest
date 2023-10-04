package com.cesarwillymc.jpmorgantest.domain.usecase.entities

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
data class WeatherDetail(
    val cityName: String,
    val temperature: Double?,
    val weatherDescription: String,
    val humidity: Int?,
    val pressure: Int?,
    val windSpeed: Double?,
    val sunrise: Long,
    val sunset: Long,
    val icon: String?
)
