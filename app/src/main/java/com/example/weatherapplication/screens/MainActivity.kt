package com.example.weatherapplication.screens

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.data.entities.WeatherResponse
import com.example.weatherapplication.util.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var mainViewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.weather.observe(this) {
            it.enqueue(object: Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful){
                        Log.d("MyTag", "${response.body()}")
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

                }

            })
        }
    }
}