package com.weatherapp.weatherapp.service

import com.weatherapp.domain.WeatherData

interface WeatherService {
    suspend fun getWeatherForLocation(location: String): WeatherData?
    suspend fun updateWeatherCache()
    fun getAllLocations() : List<String>
}