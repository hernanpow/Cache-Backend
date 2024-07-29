package com.weatherapp.weatherapp.service

import com.weatherapp.weatherapp.client.WeatherApiClient
import com.weatherapp.config.AppConfig
import com.weatherapp.domain.WeatherData
import com.weatherapp.repository.WeatherRepository
import com.weatherapp.weatherapp.utils.ErrorLogger
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class WeatherServiceImpl(
    private val repository: WeatherRepository,
    private val config: AppConfig,
    private val apiClient: WeatherApiClient,
    private val errorLogger: ErrorLogger
) : WeatherService {

    override suspend fun getWeatherForLocation(location: String): WeatherData? {
        return repository.getWeatherData(location)
    }

    override suspend fun updateWeatherCache() {
        config.locations.forEach { location ->
            try {
                retry(3, 1000) {
                    if (Random.nextDouble() < 0.2) throw Exception("API request failed")
                    val weatherData = apiClient.getWeatherData(location)
                    repository.saveWeatherData(weatherData)
                }
            } catch (e: Exception) {
                println("Error updating weather for $location: ${e.message}")
                errorLogger.logError("Failed to update weather for $location: ${e.message}")
            }
        }
    }

    private suspend fun <T> retry(
        retries: Int,
        delayMillis: Long,
        block: suspend () -> T
    ): T {
        repeat(retries - 1) {
            try {
                return block()
            } catch (e: Exception) {
                delay(delayMillis)
            }
        }
        return block() // Last attempt
    }
}
