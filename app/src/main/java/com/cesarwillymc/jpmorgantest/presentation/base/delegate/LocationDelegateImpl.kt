package com.cesarwillymc.jpmorgantest.presentation.base.delegate

import com.cesarwillymc.jpmorgantest.di.MainDispatcher
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * Created by cesarwillymamanicanaza on 7/06/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class LocationDelegateImpl @Inject constructor(
    @MainDispatcher coroutineDispatcher: CoroutineDispatcher
) : LocationDelegate,
    CoroutineScope by CoroutineScope(coroutineDispatcher) {

    private val _weatherDetail: MutableStateFlow<WeatherDetail?> = MutableStateFlow(null)
    override val weatherDetail: StateFlow<WeatherDetail?>
        get() = _weatherDetail

    override fun saveWeatherDetail(detail: WeatherDetail) {
        _weatherDetail.update { detail }
    }
}
