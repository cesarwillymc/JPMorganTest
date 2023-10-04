package com.cesarwillymc.jpmorgantest.data.sources.search

import com.cesarwillymc.jpmorgantest.data.sources.search.local.SearchLocalDataSource
import com.cesarwillymc.jpmorgantest.data.sources.search.mapper.SearchResultMapper
import com.cesarwillymc.jpmorgantest.data.sources.search.remote.SearchRemoteDataSource
import com.cesarwillymc.jpmorgantest.util.constants.ONE
import com.cesarwillymc.jpmorgantest.util.extension.formatOnlyUS
import com.cesarwillymc.jpmorgantest.util.state.Result
import com.cesarwillymc.jpmorgantest.util.state.dataOrNull
import com.cesarwillymc.jpmorgantest.util.state.isError
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import com.cesarwillymc.jpmorgantest.utils.MockkTest
import com.cesarwillymc.jpmorgantest.utils.data.SearchDataGenerator
import com.cesarwillymc.jpmorgantest.utils.data.SearchDomainGenerator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest : MockkTest() {
    private lateinit var searchDataSource: SearchDataSource

    @RelaxedMockK
    private lateinit var remoteDataSource: SearchRemoteDataSource

    @RelaxedMockK
    private lateinit var searchResultMapper: SearchResultMapper

    @RelaxedMockK
    private lateinit var searchLocalDataSource: SearchLocalDataSource

    @Before
    fun setUp() {
        searchDataSource =
            SearchRepository(remoteDataSource, searchResultMapper, searchLocalDataSource)
    }

    @Test
    fun searchFilterSuccess() = runTest {
        coEvery {
            remoteDataSource.search(
                formatOnlyUS(
                    SearchDataGenerator.city,
                    SearchDataGenerator.stateCode,
                    SearchDataGenerator.countryCode
                )
            )
        } returns Result.Success(SearchDataGenerator.weatherData)

        every {
            searchResultMapper.fromResponseToDomain(SearchDataGenerator.weatherData)
        } returns SearchDomainGenerator.weatherDomain
        searchDataSource.searchByQuery(
            SearchDataGenerator.city,
            SearchDataGenerator.stateCode,
            SearchDataGenerator.countryCode
        ).let {
            Assert.assertEquals(it.dataOrNull(), SearchDomainGenerator.weatherDomain)
            Assert.assertTrue(it.isSuccess)
        }
    }

    @Test
    fun searchFilterError() = runTest {
        coEvery {
            remoteDataSource.search(
                SearchDataGenerator.querySearch
            )
        } returns Result.Error(Exception())

        searchDataSource.searchByQuery(
            SearchDataGenerator.city,
            SearchDataGenerator.stateCode,
            SearchDataGenerator.countryCode
        ).let {
            Assert.assertTrue(it.isError)
        }
    }

    @Test
    fun recentlySearched() = runTest {
        coEvery { searchLocalDataSource.recentlySearched() } returns Result.Success(
            formatOnlyUS(
                SearchDataGenerator.city,
                SearchDataGenerator.stateCode,
                SearchDataGenerator.countryCode
            )
        )

        searchDataSource.recentlySearched().let {
            Assert.assertTrue(it.isSuccess)
            Assert.assertEquals(it.dataOrNull(), SearchDataGenerator.querySearch)
        }
        // Check verify
        coVerify(exactly = ONE) {
            searchLocalDataSource.recentlySearched()
        }
    }

    @Test
    fun recentlySearchedError() = runTest {
        coEvery { searchLocalDataSource.recentlySearched() } returns Result.Error(Exception())

        searchDataSource.recentlySearched().let {
            Assert.assertTrue(it.isError)
        }
        // Check verify
        coVerify(exactly = ONE) {
            searchLocalDataSource.recentlySearched()
        }
    }

    @Test
    fun saveQuery() = runTest {
        coEvery {
            searchLocalDataSource.saveQuery(
                formatOnlyUS(
                    SearchDataGenerator.city,
                    SearchDataGenerator.stateCode,
                    SearchDataGenerator.countryCode
                )
            )
        } returns Result.Success(Unit)

        searchDataSource.saveQuery(
            SearchDataGenerator.city,
            SearchDataGenerator.stateCode,
            SearchDataGenerator.countryCode
        ).let {
            Assert.assertTrue(it.isSuccess)
        }
    }

    @Test
    fun saveQueryError() = runTest {
        coEvery {
            searchLocalDataSource.saveQuery(
                SearchDataGenerator.querySearch
            )
        } returns Result.Error(Exception())

        searchDataSource.saveQuery(
            SearchDataGenerator.city,
            SearchDataGenerator.stateCode,
            SearchDataGenerator.countryCode
        ).let {
            Assert.assertTrue(it.isError)
        }
    }
}
