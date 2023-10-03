package com.cesarwillymc.jpmorgantest.data.sources.search.local
import com.cesarwillymc.jpmorgantest.util.state.Result
interface SearchLocalDataSource {
    fun saveQuery(value: String): Result<Unit>
    fun recentlySearched(): Result<String>
}
