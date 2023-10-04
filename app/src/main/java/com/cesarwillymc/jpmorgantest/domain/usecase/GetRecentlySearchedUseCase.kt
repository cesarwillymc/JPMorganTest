package com.cesarwillymc.jpmorgantest.domain.usecase

import com.cesarwillymc.jpmorgantest.data.sources.search.SearchDataSource
import com.cesarwillymc.jpmorgantest.domain.base.SuspendNoParamsUseCase
import com.cesarwillymc.jpmorgantest.util.state.Result
import javax.inject.Inject

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class GetRecentlySearchedUseCase @Inject constructor(
    private val repository: SearchDataSource
) : SuspendNoParamsUseCase<String>() {
    override fun execute(): Result<String> {
        return repository.recentlySearched()
    }
}
