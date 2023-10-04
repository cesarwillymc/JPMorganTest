package com.cesarwillymc.jpmorgantest.data.sources.network.util.error

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
sealed class ErrorSource : Exception() {

    object Network : ErrorSource()

    object Unknown : ErrorSource()

    data class ServiceError(
        val errorCode: String,
        val errorMessage: String?
    ) : ErrorSource()
}
