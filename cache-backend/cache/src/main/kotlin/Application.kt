package com.weatherapp

import com.weatherapp.weatherapp.client.TomorrowIoClient
import com.weatherapp.weatherapp.api.configureRouting
import com.weatherapp.config.AppConfig
import com.weatherapp.weatherapp.repository.RedisWeatherRepository
import com.weatherapp.weatherapp.service.WeatherServiceImpl
import com.weatherapp.weatherapp.utils.ErrorLogger
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlinx.coroutines.*

fun main(args : Array<String>) {
    val config = AppConfig.load()
    val repository = RedisWeatherRepository(config.redisUrl)
    val apiClient = TomorrowIoClient(config.weatherApiKey)
    val errorLogger = ErrorLogger(repository)
    val weatherService = WeatherServiceImpl(repository, config, apiClient, errorLogger)



    val server = embeddedServer(Netty, port = config.port) {
        configureRouting(weatherService)
    }

    runBlocking {

        launch {
            server.start(wait = false)
        }

        // Lanzar la actualización periódica en otra coroutine
        launch {
            while (true) {
                weatherService.updateWeatherCache()
                delay(5 * 60 * 1000) // 5 minutos
            }
        }
    }


}