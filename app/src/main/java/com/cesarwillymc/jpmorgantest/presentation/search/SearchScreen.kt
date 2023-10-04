package com.cesarwillymc.jpmorgantest.presentation.search

import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cesarwillymc.jpmorgantest.R
import com.cesarwillymc.jpmorgantest.presentation.search.composable.SearchContent
import com.cesarwillymc.jpmorgantest.ui.components.CustomExtendedSheetContent
import com.cesarwillymc.jpmorgantest.ui.components.CustomSimpleScaffold
import com.cesarwillymc.jpmorgantest.ui.components.SearchViewComponent
import com.cesarwillymc.jpmorgantest.util.constants.EMPTY_STRING

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navigateToDetail: () -> Unit,
    navigateUp: () -> Unit,
    navController: NavController,
    activity:Activity,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val searchStateUI by searchViewModel.searchStateUI.collectAsState()
    val textSearch = searchViewModel.textSearch
    val isOpenModal by searchViewModel.openBottomSheet.collectAsState()
    CustomSimpleScaffold(
        enableNavigationIcon = navBackStackEntry?.destination?.route != null,
        title = {
            SearchViewComponent(
                queryUiState = textSearch,
                onQueryChange = searchViewModel::onQueryChange,
                onClickBackWhenTextIsEmpty = {
                    searchViewModel.onQueryChange(EMPTY_STRING)
                }
            )
        },
        navigateUp = navigateUp
    ) {
        SearchContent(
            searchStateUI = searchStateUI,
            onLoadLocation = {
                searchViewModel.onLoadLocation(activity)
            },
            onSaveWeather = searchViewModel::onSaveWeatherDetail,
            textQuery = textSearch,
            activity = activity
        )
    }
    if (isOpenModal) {
        ModalBottomSheet(
            shape = RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.Normal100),
                topEnd = dimensionResource(id = R.dimen.Normal100)
            ),
            content = {
                CustomExtendedSheetContent(
                    icon = R.drawable.ic_wishlist_red,
                    title = stringResource(R.string.til_congratulations),
                    subtitle = stringResource(R.string.desc_congratulations),
                    forceBigSize = true,
                    onClick = {
                        searchViewModel.onCloseBottomSheet()
                        if (navBackStackEntry?.destination?.route != null) {
                            navigateToDetail()
                        } else {
                            navigateUp()
                        }
                    },
                    titleButton = stringResource(R.string.til_close)
                )
            },
            onDismissRequest = {
                searchViewModel.onCloseBottomSheet()
                if (navBackStackEntry?.destination?.route != null) {
                    navigateToDetail()
                } else {
                    navigateUp()
                }
            }
        )
    }
}