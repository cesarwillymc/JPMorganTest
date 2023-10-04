package com.cesarwillymc.jpmorgantest.data.sources.search.remote

import com.cesarwillymc.jpmorgantest.data.settings.network.util.error.ErrorSource
import com.cesarwillymc.jpmorgantest.data.sources.search.service.SearchService
import com.cesarwillymc.jpmorgantest.util.constants.FORMAT_ONLY_US
import com.cesarwillymc.jpmorgantest.util.constants.ONE
import com.cesarwillymc.jpmorgantest.util.state.dataOrNull
import com.cesarwillymc.jpmorgantest.util.state.getError
import com.cesarwillymc.jpmorgantest.util.state.isError
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import com.cesarwillymc.jpmorgantest.utils.MockkTest
import com.cesarwillymc.jpmorgantest.utils.data.SearchDataGenerator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchRemoteDataSourceImplTest : MockkTest() {

    private lateinit var searchRemoteDataSource: SearchRemoteDataSource

    @RelaxedMockK
    private lateinit var searchService: SearchService

    @Before
    fun setUp() {
        searchRemoteDataSource = SearchRemoteDataSourceImpl(searchService = searchService)
    }

    @Test
    fun searchSuccess() = runTest {
        coEvery {
            searchService.search(
                FORMAT_ONLY_US.format(SearchDataGenerator.city),
                null,
                null
            )
        } returns SearchDataGenerator.weatherData

        searchRemoteDataSource.search(
            SearchDataGenerator.querySearch
        ).let {
            Assert.assertEquals(it.dataOrNull(), SearchDataGenerator.weatherData)
            Assert.assertTrue(it.isSuccess)
        }
        // Check verify
        coVerify(exactly = ONE) {
            searchService.search(
                FORMAT_ONLY_US.format(SearchDataGenerator.city),
                null,
                null
            )
        }
    }

    @Test
    fun searchError() = runTest {
        coEvery {
            searchService.search(
                FORMAT_ONLY_US.format(SearchDataGenerator.city),
                null,
                null
            )
        } throws IOException()

        searchRemoteDataSource.search(
            SearchDataGenerator.querySearch
        ).let {
            Assert.assertTrue(it.isError)
            Assert.assertTrue(it.getError() is ErrorSource.Network)
        }
        // Check verify
        coVerify(exactly = ONE) {
            searchService.search(FORMAT_ONLY_US.format(SearchDataGenerator.city), null, null)
        }
    }
}
