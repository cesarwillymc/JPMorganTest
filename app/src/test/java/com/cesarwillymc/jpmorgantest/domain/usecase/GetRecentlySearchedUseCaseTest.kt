package com.cesarwillymc.jpmorgantest.domain.usecase

import com.cesarwillymc.jpmorgantest.data.sources.search.SearchDataSource
import com.cesarwillymc.jpmorgantest.util.state.Result
import com.cesarwillymc.jpmorgantest.util.state.isError
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import com.cesarwillymc.jpmorgantest.utils.MockkTest
import com.cesarwillymc.jpmorgantest.utils.data.SearchDataGenerator
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class GetRecentlySearchedUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var repository: SearchDataSource
    lateinit var useCase: GetRecentlySearchedUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = GetRecentlySearchedUseCase(repository)
    }

    @Test
    fun execute() = runTest {
        coEvery {
            repository.recentlySearched()
        } returns Result.Success(SearchDataGenerator.querySearch)

        Assert.assertTrue(useCase().isSuccess)
    }

    @Test
    fun executeError() = runTest {
        coEvery {
            repository.recentlySearched()
        } returns Result.Error(Exception())

        Assert.assertTrue(useCase().isError)
    }
}
