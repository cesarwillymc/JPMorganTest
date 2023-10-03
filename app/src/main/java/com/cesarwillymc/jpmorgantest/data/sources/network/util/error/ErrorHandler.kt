package com.cesarwillymc.jpmorgantest.data.sources.network.util.error

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorSource
}
