package com.kardiga.openweatherapp.data.api

import com.kardiga.openweatherapp.data.models.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface OpenWeatherApi {

    @GET("/data/2.5/forecast")
    fun getFiveDayWeatherForecast(
        @Query("id") id: String,
        @Query("appid") appKey: String,
        @Query("lang") lang: String
    ): Call<WeatherForecast>

}