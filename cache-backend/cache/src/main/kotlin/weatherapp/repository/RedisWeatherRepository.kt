package com.weatherapp.weatherapp.repository

import com.weatherapp.domain.WeatherData
import com.weatherapp.repository.WeatherRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis
import java.text.SimpleDateFormat
import java.util.*

class RedisWeatherRepository(private val redisUrl: String) : WeatherRepository {
    private val jedis = Jedis(redisUrl)

    override suspend fun saveWeatherData(data: WeatherData) {
        jedis.set(data.location, Json.encodeToString(data))
    }

    override suspend fun getWeatherData(location: String): WeatherData? {
        val json = jedis.get(location)
        return json?.let { Json.decodeFromString<WeatherData>(it) }
    }

    override suspend fun logError(error: String) {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentDate = sdf.format(Date())
        jedis.lpush("error_log", "$currentDate: $error")
    }


}