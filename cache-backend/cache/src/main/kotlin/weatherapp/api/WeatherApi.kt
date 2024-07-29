package com.weatherapp.weatherapp.api

import com.weatherapp.weatherapp.service.WeatherService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*

fun Application.configureRouting(weatherService: WeatherService) {
    val locationMapper = LocationMapper()

    routing {
     //   swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
     //   openAPI(path="openapi", swaggerFile = "openapi/documentation.yaml")

        get("/weather/{searchTerm}") {
            val searchTerm = call.parameters["searchTerm"] ?: return@get call.respondText("Missing search term", status = HttpStatusCode.BadRequest)

            val fullLocation = locationMapper.mapSearchTermToFullLocation(searchTerm)

            if (fullLocation == null) {
                call.respondText("No location found matching '$searchTerm'", status = HttpStatusCode.NotFound)
                return@get
            }

            val weatherData = weatherService.getWeatherForLocation(fullLocation)
            if (weatherData != null) {
                call.respondText("The temperature in  $fullLocation is ${weatherData.values.temperature}", status = HttpStatusCode.OK)
            } else {
                call.respondText("Weather data not found for $fullLocation", status = HttpStatusCode.NotFound)
            }
        }
    }
}