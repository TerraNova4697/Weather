package com.example.weatherapplication.di.application

import com.example.weatherapplication.data.Constants
import com.example.weatherapplication.network.WeatherServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun weatherService(weatherRetrofit: Retrofit): WeatherServiceApi = weatherRetrofit.create(WeatherServiceApi::class.java)

    @Provides
    fun weatherRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}