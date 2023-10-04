package com.cesarwillymc.jpmorgantest.presentation.base.delegate

import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by cesarwillymamanicanaza on 7/06/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface LocationDelegate {

    val weatherDetail: StateFlow<WeatherDetail?>
    fun saveWeatherDetail(detail: WeatherDetail)
}
