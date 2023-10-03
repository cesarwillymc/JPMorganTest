package com.cesarwillymc.jpmorgantest.data.di

import com.cesarwillymc.jpmorgantest.BuildConfig
import com.cesarwillymc.jpmorgantest.data.sources.network.WeatherInterceptor
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
    fun providesApiKeyOkhttp(
        interceptor: WeatherInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(interceptor = interceptor)
        .build()

    @Singleton
    @Provides
    fun providesSearchService(
        okHttpClient: OkHttpClient
    ) = provideService<SearchService>(okHttpClient)

    private inline fun <reified T : Any> provideService(
        okhttpClient: OkHttpClient
    ): T {
        return providesRetrofit(okhttpClient).create(T::class.java)
    }
}
