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
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
class SaveLastSearchUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var repository: SearchDataSource
    lateinit var useCase: SaveLastSearchUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = SaveLastSearchUseCase(repository, UnconfinedTestDispatcher())
    }

    @Test
    fun execute() = runTest {
        coEvery {
            repository.saveQuery(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        } returns Result.Success(Unit)

        Assert.assertTrue(
            useCase(
                SaveLastSearchUseCase.Params(
                    SearchDataGenerator.city,
                    SearchDataGenerator.stateCode,
                    SearchDataGenerator.countryCode
                )
            ).isSuccess
        )
    }

    @Test
    fun executeError() = runTest {
        coEvery {
            repository.saveQuery(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        } returns Result.Error(Exception())

        Assert.assertTrue(
            useCase(
                SaveLastSearchUseCase.Params(
                    SearchDataGenerator.city,
                    SearchDataGenerator.stateCode,
                    SearchDataGenerator.countryCode
                )
            ).isError
        )
    }
}
