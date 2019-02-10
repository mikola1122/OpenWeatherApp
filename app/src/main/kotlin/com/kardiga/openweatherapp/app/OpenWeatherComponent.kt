package com.kardiga.openweatherapp.app

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface OpenWeatherComponent : OpenWeatherGraph {
    /**
     * An initializer that creates the graph from an application.
     */
    object Initializer {
        fun init(app: OpenWeatherApp): OpenWeatherGraph{
            return DaggerOpenWeatherComponent.builder()
                .appModule(AppModule(app))
                .build()
        }
    }
}