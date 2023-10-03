package com.cesarwillymc.jpmorgantest.data.sources.search.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.cesarwillymc.jpmorgantest.data.sources.search.utils.MockkTest
import com.cesarwillymc.jpmorgantest.data.sources.search.utils.data.SearchDataGenerator
import com.cesarwillymc.jpmorgantest.util.constants.EMPTY_STRING
import com.cesarwillymc.jpmorgantest.util.constants.ONE
import com.cesarwillymc.jpmorgantest.util.state.dataOrNull
import com.cesarwillymc.jpmorgantest.util.state.isError
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchLocalDataSourceImplTest : MockkTest() {

    private lateinit var searchLocalDataSource: SearchLocalDataSource

    @RelaxedMockK
    private lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setUp() {
        searchLocalDataSource = SearchLocalDataSourceImpl(sharedPreferences)
    }

    @Test
    fun recentlySearched() = runTest {
        coEvery {
            sharedPreferences.getString(SearchLocalDataSourceImpl.RECENTLY_SEARCHED, EMPTY_STRING)
        } returns SearchDataGenerator.querySearch

        searchLocalDataSource.recentlySearched().let {
            assertTrue(it.isSuccess)
            assertEquals(it.dataOrNull(), SearchDataGenerator.querySearch)
        }
        // Check verify
        verify(exactly = ONE) {
            sharedPreferences.getString(
                SearchLocalDataSourceImpl.RECENTLY_SEARCHED,
                EMPTY_STRING
            )
        }
    }

    @Test
    fun recentlySearchedError() = runTest {
        coEvery {
            sharedPreferences.getString(
                SearchLocalDataSourceImpl.RECENTLY_SEARCHED,
                EMPTY_STRING
            )
        } throws Exception()

        searchLocalDataSource.recentlySearched().let {
            assertTrue(it.isError)
        }
        // Check verify
        verify(
            exactly = ONE
        ) {
            sharedPreferences.getString(SearchLocalDataSourceImpl.RECENTLY_SEARCHED, EMPTY_STRING)
        }
    }

    @Test
    fun saveQuery() = runTest {
        coEvery {
            sharedPreferences.edit {
                putString(
                    SearchLocalDataSourceImpl.RECENTLY_SEARCHED,
                    SearchDataGenerator.querySearch
                )
            }
        } returns Unit

        searchLocalDataSource.saveQuery(
            SearchDataGenerator.querySearch
        ).let {
            assertTrue(it.isSuccess)
        }
    }

    @Test
    fun saveQueryError() = runTest {
        coEvery {
            sharedPreferences.edit {
                putString(
                    SearchLocalDataSourceImpl.RECENTLY_SEARCHED,
                    SearchDataGenerator.querySearch
                )
            }
        } throws Exception()

        searchLocalDataSource.saveQuery(
            SearchDataGenerator.querySearch
        ).let {
            assertTrue(it.isError)
        }
    }
}
