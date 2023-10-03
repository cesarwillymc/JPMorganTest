package com.cesarwillymc.jpmorgantest.domain.usecase

import com.cesarwillymc.jpmorgantest.data.sources.search.SearchDataSource
import com.cesarwillymc.jpmorgantest.di.IoDispatcher
import com.cesarwillymc.jpmorgantest.domain.base.SuspendUseCase
import com.cesarwillymc.jpmorgantest.domain.usecase.entities.WeatherDetail
import com.cesarwillymc.jpmorgantest.util.constants.EMPTY_STRING
import com.cesarwillymc.jpmorgantest.util.state.Result
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class SaveLastSearchUseCase @Inject constructor(
    private val repository: SearchDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<SaveLastSearchUseCase.Params, Unit>(
    coroutineDispatcher = dispatcher
) {
    data class Params(
        val city: String,
        val state: String = EMPTY_STRING,
        val country: String = EMPTY_STRING
    )

    override suspend fun execute(parameters: Params): Result<Unit> {
        return repository.saveQuery(parameters.city, parameters.state, parameters.country)
    }
}
