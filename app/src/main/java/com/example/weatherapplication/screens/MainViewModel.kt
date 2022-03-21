package com.example.weatherapplication.screens

import androidx.lifecycle.*
import com.example.weatherapplication.data.Constants
import com.example.weatherapplication.data.entities.WeatherResponse
import com.example.weatherapplication.network.WeatherServiceApi
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(
    private val weatherServiceApi: WeatherServiceApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _weather = MutableLiveData<Call<WeatherResponse>>()
    val weather: LiveData<Call<WeatherResponse>> = _weather

    init {
        viewModelScope.launch {
            _weather.value = weatherServiceApi.getWeather(cityName = "berlin", Constants.METRIC_UNIT, Constants.APP_ID)
        }
    }




}