package com.cesarwillymc.jpmorgantest.data.sources.search.local
import com.cesarwillymc.jpmorgantest.util.state.Result
/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
interface SearchLocalDataSource {
    fun saveQuery(value: String): Result<Unit>
    fun recentlySearched(): Result<String>
}
