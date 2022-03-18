package com.example.weatherapplication.di.application

import com.example.weatherapplication.di.activity.ActivityComponent
import com.example.weatherapplication.di.activity.ActivityModule
import com.example.weatherapplication.screens.MainActivity
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface ApplicationComponent  {

    fun newActivityComponent(activityModule: ActivityModule) : ActivityComponent
//    fun weatherService(): WeatherServiceApi


}