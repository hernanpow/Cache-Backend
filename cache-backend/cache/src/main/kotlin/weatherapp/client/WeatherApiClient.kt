package com.weatherapp.weatherapp.client

import com.weatherapp.domain.WeatherData

interface WeatherApiClient {
    suspend fun getWeatherData(location: String): WeatherData
}