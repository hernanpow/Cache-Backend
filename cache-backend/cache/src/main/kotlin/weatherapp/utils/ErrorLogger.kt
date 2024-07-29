package com.weatherapp.weatherapp.utils

import com.weatherapp.repository.WeatherRepository

class ErrorLogger(private val repository: WeatherRepository) {
    suspend fun logError(message: String) {
        repository.logError(message)
    }
}