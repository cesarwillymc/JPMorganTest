package com.cesarwillymc.jpmorgantest.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail

/**
 * Created by Cesar Canaza on 10/4/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@Composable
fun DetailContent(
    detail: WeatherDetail?
) {
    requireNotNull(detail)
    Column {
        Text(text = detail.name)
    }
}