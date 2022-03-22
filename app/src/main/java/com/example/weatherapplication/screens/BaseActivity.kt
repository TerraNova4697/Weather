package com.example.weatherapplication.screens

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapplication.WeatherApplication
import com.example.weatherapplication.di.activity.ActivityModule


open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as WeatherApplication).appComponent

    private val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    protected val injector get() = activityComponent

}