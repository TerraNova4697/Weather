package com.example.weatherapplication

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapplication.di.application.AppModule
import com.example.weatherapplication.di.application.ApplicationComponent
import com.example.weatherapplication.di.application.DaggerApplicationComponent


class WeatherApplication : Application() {


    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}