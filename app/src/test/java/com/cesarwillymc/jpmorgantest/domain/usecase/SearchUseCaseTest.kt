package com.cesarwillymc.jpmorgantest.domain.usecase

import com.cesarwillymc.jpmorgantest.data.sources.search.SearchDataSource
import com.cesarwillymc.jpmorgantest.util.state.Result
import com.cesarwillymc.jpmorgantest.util.state.isError
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import com.cesarwillymc.jpmorgantest.utils.MockkTest
import com.cesarwillymc.jpmorgantest.utils.data.SearchDataGenerator
import com.cesarwillymc.jpmorgantest.utils.data.SearchDomainGenerator
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class SearchUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var repository: SearchDataSource
    lateinit var useCase: SearchUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = SearchUseCase(repository, UnconfinedTestDispatcher())
    }

    @Test
    fun execute() = runTest {
        coEvery {
            repository.searchFilter(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        } returns Result.Success(SearchDomainGenerator.weatherDomain)

        useCase(
            SearchUseCase.Params(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        ).let {
            Assert.assertTrue(it.isSuccess)
        }
    }

    @Test
    fun executeErrorParams() = runTest {
        coEvery {
            repository.searchFilter(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        } returns Result.Success(SearchDomainGenerator.weatherDomain)

        useCase(
            SearchUseCase.Params(
                SearchDataGenerator.city
            )
        ).let {
            Assert.assertTrue(it.isError)
        }
    }

    @Test
    fun executeError() = runTest {
        coEvery {
            repository.searchFilter(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        } returns Result.Error(Exception())

        useCase(
            SearchUseCase.Params(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        ).let {
            Assert.assertTrue(it.isError)
        }
    }
}
