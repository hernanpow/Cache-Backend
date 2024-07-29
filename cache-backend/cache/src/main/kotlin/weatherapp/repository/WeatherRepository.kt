package com.weatherapp.repository

import com.weatherapp.domain.WeatherData

interface WeatherRepository {
    suspend fun saveWeatherData(data: WeatherData)
    suspend fun getWeatherData(location: String): WeatherData?
    suspend fun logError(error: String)
}