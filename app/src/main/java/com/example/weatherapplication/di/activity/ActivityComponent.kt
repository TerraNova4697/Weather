package com.example.weatherapplication.di.activity

import com.example.weatherapplication.screens.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}