package com.kardiga.openweatherapp.app

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.kardiga.openweatherapp.BuildConfig
import com.kardiga.openweatherapp.data.OpenWeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule constructor(private val app: OpenWeatherApp) {

    private val CONNECTION_TIMEOUT: Long = 30  // seconds

    @Provides
    @Singleton
    fun provideApplication() = app as Application

    @Provides
    @Singleton
    fun provideContext() = app as Context


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder: OkHttpClient.Builder =
            OkHttpClient.Builder().connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(client: OkHttpClient): OpenWeatherApi {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_OPEN_WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit.create<OpenWeatherApi>(OpenWeatherApi::class.java)
    }

}