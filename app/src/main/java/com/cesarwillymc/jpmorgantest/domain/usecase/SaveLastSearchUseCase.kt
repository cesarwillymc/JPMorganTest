package com.cesarwillymc.jpmorgantest.domain.usecase

import com.cesarwillymc.jpmorgantest.data.sources.search.SearchDataSource
import com.cesarwillymc.jpmorgantest.di.IoDispatcher
import com.cesarwillymc.jpmorgantest.domain.base.SuspendUseCase
import com.cesarwillymc.jpmorgantest.util.state.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class SaveLastSearchUseCase @Inject constructor(
    private val repository: SearchDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<String, Unit>(
    coroutineDispatcher = dispatcher
) {
    override suspend fun execute(parameters: String): Result<Unit> {
        return repository.saveQuery(parameters)
    }
}
