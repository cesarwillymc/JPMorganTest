package com.cesarwillymc.jpmorgantest.presentation.search.state

import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
data class SearchStateUI(
    var isLoading: Boolean = false,
    var isError: Boolean = false,
    var isSuccess: Boolean = false,
    var detailWeather: WeatherDetail? = null
)
