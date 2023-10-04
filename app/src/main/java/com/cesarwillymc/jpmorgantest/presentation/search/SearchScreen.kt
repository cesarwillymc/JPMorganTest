package com.cesarwillymc.jpmorgantest.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@Composable
fun SearchScreen(
    navigateToDetail: () -> Unit,
    navigateUp: () -> Unit,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
}