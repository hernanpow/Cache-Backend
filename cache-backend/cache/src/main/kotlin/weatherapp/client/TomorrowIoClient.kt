package com.weatherapp.weatherapp.client

import com.weatherapp.domain.WeatherData
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class TomorrowIoClient(private val apiKey: String) : WeatherApiClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
            })
        }
    }

    override suspend fun getWeatherData(location: String): WeatherData {
        val response: HttpResponse = client.get("https://api.tomorrow.io/v4/weather/realtime") {
            parameter("location", location)
            parameter("apikey", apiKey)
        }

        val responseBody = response.bodyAsText()
        println("API Response: $responseBody") // Log the raw response

        val tomorrowIoResponse = Json.decodeFromString<TomorrowIoResponse>(responseBody)

        return WeatherData(
            location = tomorrowIoResponse.location.name,
            time = tomorrowIoResponse.data.time,
            values = tomorrowIoResponse.data.values
        )
    }
}

@Serializable
data class TomorrowIoResponse(
    val data: DataResponse,
    val location: Location
)

@Serializable
data class DataResponse(
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

@Serializable
data class Location(
    val lat: Double,
    val lon: Double,
    val name: String,
    val type: String
)