package com.example.weatherapplication.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapplication.WeatherApplication
import com.example.weatherapplication.di.activity.ActivityModule

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as WeatherApplication).appComponent

    private val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    protected val injector get() = activityComponent

}