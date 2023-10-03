package com.cesarwillymc.jpmorgantest.data.sources.search.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.cesarwillymc.jpmorgantest.util.constants.EMPTY_STRING
import com.cesarwillymc.jpmorgantest.util.state.Result
import javax.inject.Inject

class SearchLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SearchLocalDataSource {
    override fun saveQuery(value: String): Result<Unit> {
        sharedPreferences.edit {
            if (value.isEmpty()) {
                remove(RECENTLY_SEARCHED)
            } else {
                putString(RECENTLY_SEARCHED, value)
            }
        }
        return Result.Success(Unit)
    }

    override fun recentlySearched(): Result<String> {
        return try {
            return Result.Success(
                sharedPreferences.getString(
                    RECENTLY_SEARCHED,
                    EMPTY_STRING
                ).orEmpty()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
    companion object {
        const val RECENTLY_SEARCHED = "recently_searched"
    }
}
