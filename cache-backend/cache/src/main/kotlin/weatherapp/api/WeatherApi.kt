package com.weatherapp.weatherapp.api


import com.weatherapp.weatherapp.service.WeatherService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(weatherService: WeatherService) {
    routing {
        get("/weather/{location}") {
            val location = call.parameters["location"] ?: return@get call.respondText("Missing location")
            val weatherData = weatherService.getWeatherForLocation(location)
            if (weatherData != null) {
                call.respond(weatherData)
            } else {
                call.respondText("Weather data not found for $location")
            }
        }
    }
}