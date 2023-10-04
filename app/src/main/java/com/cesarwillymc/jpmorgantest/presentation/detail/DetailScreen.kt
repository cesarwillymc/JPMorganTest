package com.cesarwillymc.jpmorgantest.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cesarwillymc.jpmorgantest.R
import com.cesarwillymc.jpmorgantest.presentation.detail.components.DetailContent
import com.cesarwillymc.jpmorgantest.presentation.detail.components.TopBarDetailScreen
import com.cesarwillymc.jpmorgantest.ui.components.CustomFullScreenLoading
import com.cesarwillymc.jpmorgantest.ui.components.CustomLottieMessage
import com.cesarwillymc.jpmorgantest.ui.components.CustomSimpleScaffold

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@Composable
fun DetailScreen(
    navigateToSearch: () -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val detailScreen by detailViewModel.weatherDetail.collectAsState()
    val detailStateUI by detailViewModel.detailStateUI.collectAsState()
    CustomSimpleScaffold(
        enableNavigationIcon = false,
        title = {
            TopBarDetailScreen(
                navigateToSearch = navigateToSearch,
                title = detailScreen?.cityName.orEmpty()
            )
        }
    ) {
        if (detailStateUI.isLoading) {
            CustomFullScreenLoading(true)
        } else if (detailStateUI.isError || detailScreen == null) {
            CustomLottieMessage(
                R.raw.animation_error,
                title = stringResource(R.string.til_error),
                message = stringResource(R.string.desc_error),
                showRetryButton = true,
                onClickRetry = detailViewModel::loadRecentlySearched
            )
        } else {
            DetailContent(
                detail = detailScreen
            )
        }
    }
}
