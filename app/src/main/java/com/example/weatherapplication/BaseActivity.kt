package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as WeatherApplication).appComponent

    protected val injector get() = appComponent

}