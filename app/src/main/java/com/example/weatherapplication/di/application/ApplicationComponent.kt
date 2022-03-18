package com.example.weatherapplication.di.application

import com.example.weatherapplication.MainActivity
import com.example.weatherapplication.network.WeatherServiceApi
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface ApplicationComponent  {

//    fun weatherService(): WeatherServiceApi
    fun inject(activity: MainActivity)

}