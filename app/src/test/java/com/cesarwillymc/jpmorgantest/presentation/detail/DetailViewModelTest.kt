package com.cesarwillymc.jpmorgantest.presentation.detail

import app.cash.turbine.test
import com.cesarwillymc.jpmorgantest.domain.usecase.GetRecentlySearchedUseCase
import com.cesarwillymc.jpmorgantest.domain.usecase.SearchByQueryUseCase
import com.cesarwillymc.jpmorgantest.presentation.base.delegate.LocationDelegate
import com.cesarwillymc.jpmorgantest.util.state.Result
import com.cesarwillymc.jpmorgantest.utils.BaseViewModelTest
import com.cesarwillymc.jpmorgantest.utils.data.SearchDataGenerator
import com.cesarwillymc.jpmorgantest.utils.data.SearchDomainGenerator
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Cesar Canaza on 10/4/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest : BaseViewModelTest() {

    @RelaxedMockK
    private lateinit var getRecentlySearchedUseCase: GetRecentlySearchedUseCase

    @RelaxedMockK
    private lateinit var searchByQueryUseCase: SearchByQueryUseCase

    @RelaxedMockK
    private lateinit var locationDelegate: LocationDelegate

    private lateinit var viewModel: DetailViewModel

    @Before
    fun onSetup() {
        coEvery { locationDelegate.weatherDetail } returns MutableStateFlow(SearchDomainGenerator.weatherDomain)
        viewModel =
            DetailViewModel(getRecentlySearchedUseCase, searchByQueryUseCase, locationDelegate)
    }

    @Test
    fun onInit() {
        Assert.assertTrue(viewModel.weatherDetail.value != null)
    }

    @Test
    fun loadRecentlySearched() = runTest {
        coEvery { getRecentlySearchedUseCase(Unit) } returns Result.Success(SearchDataGenerator.city)
        coEvery { searchByQueryUseCase(SearchDataGenerator.city) } returns Result.Success(
            SearchDomainGenerator.weatherDomain
        )
        coEvery { locationDelegate.saveWeatherDetail(SearchDomainGenerator.weatherDomain) } returns Unit
        viewModel.loadRecentlySearched()
        viewModel.detailStateUI.test {
            val result = awaitItem()
            Assert.assertFalse(result.isLoading)
            Assert.assertFalse(result.isError)
        }
    }

    @Test
    fun loadRecentlySearchedError() = runTest {
        coEvery { getRecentlySearchedUseCase(Unit) } returns Result.Success(SearchDataGenerator.city)
        coEvery { searchByQueryUseCase(SearchDataGenerator.city) } returns Result.Error(
            Exception()
        )
        viewModel.loadRecentlySearched()
        viewModel.detailStateUI.test {
            val result = expectMostRecentItem()
            Assert.assertFalse(result.isLoading)
            Assert.assertFalse(result.isError)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
