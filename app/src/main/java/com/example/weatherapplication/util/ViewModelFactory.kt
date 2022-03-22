package com.example.weatherapplication.util

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.weatherapplication.data.prefs.UserPreferencesRepository
import com.example.weatherapplication.network.WeatherServiceApi
import com.example.weatherapplication.screens.MainViewModel
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val weatherServiceApiProvider: Provider<WeatherServiceApi>,
    savedStateRegistryOwner: SavedStateRegistryOwner,
    private val userPreferencesRepositoryProvider: Provider<UserPreferencesRepository>
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(weatherServiceApiProvider.get(), handle, userPreferencesRepositoryProvider.get()) as T
            else -> throw RuntimeException("Unsupported ViewModel type: $modelClass")
        }
    }

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return when (modelClass) {
//            MainViewModel::class.java -> mainViewModelProvider.get() as T
//            else -> throw RuntimeException("Unsupported ViewModel type: $modelClass")
//        }
//    }
}