package com.cesarwillymc.jpmorgantest.data.di

import com.cesarwillymc.jpmorgantest.BuildConfig
import com.cesarwillymc.jpmorgantest.data.settings.network.WeatherInterceptor
import com.cesarwillymc.jpmorgantest.data.settings.network.util.DefaultClient
import com.cesarwillymc.jpmorgantest.data.sources.search.service.SearchService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun gson() = Gson()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @DefaultClient
    fun providesApiKeyOkhttp(
        interceptor: WeatherInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(interceptor = interceptor)
        .build()

    @Singleton
    @Provides
    fun providesSearchService(
        @DefaultClient okHttpClient: OkHttpClient
    ) = provideService<SearchService>(okHttpClient)

    private inline fun <reified T : Any> provideService(
        okhttpClient: OkHttpClient
    ): T {
        return providesRetrofit(okhttpClient).create(T::class.java)
    }
}
