package com.kardiga.openweatherapp.app

import android.content.Context
import android.support.multidex.MultiDexApplication

class OpenWeatherApp : MultiDexApplication() {

    protected lateinit var component: OpenWeatherGraph

    override fun onCreate() {
        super.onCreate()
        buildComponentAndInject()
    }

    // Dagger 2
    protected fun buildComponentAndInject() {
        component = OpenWeatherComponent.Initializer.init(this)
        component.inject(this)
    }

    // Dagger 2
    fun component() = component

    companion object {
        // Dagger 2
        operator fun get(context: Context) = context.getApplicationContext() as OpenWeatherApp
    }
}