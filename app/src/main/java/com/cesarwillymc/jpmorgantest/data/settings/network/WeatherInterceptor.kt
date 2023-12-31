package com.cesarwillymc.jpmorgantest.data.settings.network

import com.cesarwillymc.jpmorgantest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
class WeatherInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter(TOKEN_KEY, BuildConfig.WEATHER_KEY)
            .build()

        return chain.proceed(chain.request().newBuilder().url(url).build())
    }
    companion object {
        const val TOKEN_KEY = "appid"
    }
}
