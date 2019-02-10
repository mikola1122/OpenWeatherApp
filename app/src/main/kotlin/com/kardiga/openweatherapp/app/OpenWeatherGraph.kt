package com.kardiga.openweatherapp.app

import com.kardiga.openweatherapp.MainActivity

interface OpenWeatherGraph {

    fun inject(application: OpenWeatherApp)

    // Presenters

    // Activities
    fun inject(activity: MainActivity)

    // Fragments

    // Services

    // Other
}