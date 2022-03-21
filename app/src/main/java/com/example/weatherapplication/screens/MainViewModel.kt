package com.example.weatherapplication.screens

import androidx.lifecycle.*
import com.example.weatherapplication.data.Constants
import com.example.weatherapplication.data.entities.WeatherResponse
import com.example.weatherapplication.network.WeatherServiceApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(
    private val weatherServiceApi: WeatherServiceApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _weather : MutableLiveData<WeatherResponse> = savedStateHandle.getLiveData("weather")
    val weather: LiveData<WeatherResponse> = _weather

    init {
        viewModelScope.launch {
            val response = weatherServiceApi.getWeather("berlin", Constants.METRIC_UNIT, Constants.APP_ID)
            response.enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        _weather.value = response.body()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }




}