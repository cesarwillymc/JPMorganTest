package com.cesarwillymc.jpmorgantest.presentation

import app.cash.turbine.test
import com.cesarwillymc.jpmorgantest.domain.usecase.GetRecentlySearchedUseCase
import com.cesarwillymc.jpmorgantest.ui.navigation.route.MainRoute
import com.cesarwillymc.jpmorgantest.util.state.Result
import com.cesarwillymc.jpmorgantest.utils.BaseViewModelTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

/**
 * Created by Cesar Canaza on 10/4/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : BaseViewModelTest() {

    @RelaxedMockK
    private lateinit var getRecentlySearchedUseCase: GetRecentlySearchedUseCase

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        coEvery { getRecentlySearchedUseCase() } returns Result.Error(Exception())
        viewModel = MainViewModel(getRecentlySearchedUseCase)
    }

    @Test
    fun onInitTest() {
        Assert.assertEquals(MainRoute.Search.path, viewModel.startDestination.value)
    }

    @Test
    fun getStartDestinationSearch() = runTest {
        coEvery { getRecentlySearchedUseCase() } returns Result.Error(Exception())
        viewModel.startDestination.test {
            viewModel.loadRecentlySearched()
            Assert.assertEquals(MainRoute.Search.path, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
