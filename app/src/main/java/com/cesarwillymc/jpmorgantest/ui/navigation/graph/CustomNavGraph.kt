package com.cesarwillymc.jpmorgantest.ui.navigation.graph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cesarwillymc.jpmorgantest.presentation.detail.DetailScreen
import com.cesarwillymc.jpmorgantest.presentation.search.SearchScreen
import com.cesarwillymc.jpmorgantest.ui.navigation.action.MainAction
import com.cesarwillymc.jpmorgantest.ui.navigation.deeplink.generateDeepLinks
import com.cesarwillymc.jpmorgantest.ui.navigation.route.MainRoute

@Composable
fun CustomNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    val mainActions = remember(navController) { MainAction(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = MainRoute.Detail.path,
            deepLinks = MainRoute.Detail.path.generateDeepLinks()
        ) {
            DetailScreen(
                navigateToSearch = mainActions.navigateToSearch
            )
        }

        composable(
            route = MainRoute.Search.path,
            deepLinks = MainRoute.Search.path.generateDeepLinks()
        ) {
            SearchScreen(
                navigateToDetail = mainActions.navigateToDetail,
                navigateUp = mainActions.navigateUp,
                navController = navController
            )
        }

    }
}
