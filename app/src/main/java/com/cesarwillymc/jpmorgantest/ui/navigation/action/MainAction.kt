package com.cesarwillymc.jpmorgantest.ui.navigation.action

import androidx.navigation.NavHostController
import com.cesarwillymc.jpmorgantest.ui.navigation.route.MainRoute

class MainAction(navController: NavHostController) {

    val navigateToDetail: () -> Unit = {
        navController.navigate(MainRoute.Detail.path)
    }
    val navigateToSearch: () -> Unit = {
        navController.navigate(MainRoute.Search.path)
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}
