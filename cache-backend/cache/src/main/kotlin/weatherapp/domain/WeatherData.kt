package com.weatherapp.domain


import com.weatherapp.weatherapp.client.WeatherValues
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val location: String,
    val time: String,
    val values: WeatherValues
)

