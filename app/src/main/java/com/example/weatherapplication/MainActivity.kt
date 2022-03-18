package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapplication.di.application.AppModule
import com.example.weatherapplication.di.application.DaggerApplicationComponent
import com.example.weatherapplication.network.WeatherServiceApi
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var weatherServiceApi: WeatherServiceApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}