package com.weatherapp.domain


import com.weatherapp.weatherapp.client.WeatherValues
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val location: String,
    val time: String,
    val values: WeatherValues
)

@Serializable
data class WeatherValues(
    val cloudBase: Double?,
    val cloudCeiling: Double?,
    val cloudCover: Int,
    val dewPoint: Double,
    val freezingRainIntensity: Int,
    val humidity: Int,
    val precipitationProbability: Int,
    val pressureSurfaceLevel: Double,
    val rainIntensity: Int,
    val sleetIntensity: Int,
    val snowIntensity: Int,
    val temperature: Double,
    val temperatureApparent: Double,
    val uvHealthConcern: Int,
    val uvIndex: Int,
    val visibility: Double,
    val weatherCode: Int,
    val windDirection: Double,
    val windGust: Double,
    val windSpeed: Double
)