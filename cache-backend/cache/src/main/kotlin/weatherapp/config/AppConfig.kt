package com.weatherapp.config

import io.ktor.server.config.*

data class AppConfig(
    val port: Int,
    val redisUrl: String,
    val weatherApiKey: String,
    val locations: List<String>
) {
    companion object {
        fun load(): AppConfig {
            val config = ApplicationConfig("application.conf")
            return AppConfig(
                port = config.property("ktor.deployment.port").getString().toInt(),
                redisUrl = config.property("redis.url").getString(),
                weatherApiKey = config.property("weather.api.key").getString(),
                locations = config.property("weather.locations").getList()
            )
        }
    }
}