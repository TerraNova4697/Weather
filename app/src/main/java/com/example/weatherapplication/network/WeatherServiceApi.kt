package com.example.weatherapplication.network

import com.example.weatherapplication.data.entities.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceApi {

    @GET("2.5/weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String?,
        @Query("appid") appid: String?
    ): Call<WeatherResponse>

}