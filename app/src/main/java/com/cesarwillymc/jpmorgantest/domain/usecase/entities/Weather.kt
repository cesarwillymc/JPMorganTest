package com.cesarwillymc.jpmorgantest.domain.usecase.entities

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
