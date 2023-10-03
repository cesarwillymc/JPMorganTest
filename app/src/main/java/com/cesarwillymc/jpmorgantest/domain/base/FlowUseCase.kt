package com.cesarwillymc.jpmorgantest.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
abstract class FlowUseCase<in Params, out Results>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(parameters: Params): Flow<Results> {
        return execute(parameters)
            .flowOn(coroutineDispatcher)
    }

    abstract fun execute(parameters: Params): Flow<Results>
}
