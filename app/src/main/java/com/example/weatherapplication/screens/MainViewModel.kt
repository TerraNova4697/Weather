package com.example.weatherapplication.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.network.WeatherServiceApi
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(
    private val weatherServiceApi: WeatherServiceApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {





}