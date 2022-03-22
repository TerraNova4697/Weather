package com.example.weatherapplication.di.activity

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.example.weatherapplication.data.prefs.UserPreferencesRepository
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val context: Context) {

    @Provides
    fun savedStateRegistryOwner() = context as SavedStateRegistryOwner

    @Provides
    @ActivityScope
    fun userPreferencesRepository() = UserPreferencesRepository(context)

}