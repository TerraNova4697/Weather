package com.example.weatherapplication.di.application

import com.example.weatherapplication.network.WeatherServiceApi
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
interface ApplicationComponent  {

    fun weatherService(): WeatherServiceApi

}