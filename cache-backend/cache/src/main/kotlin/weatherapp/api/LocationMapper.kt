package com.weatherapp.weatherapp.api



class LocationMapper {
    private val locationMap = mapOf(
        "auckland" to "Auckland, New Zealand / Aotearoa",
        "london" to "City of London, Greater London, England, United Kingdom",
        "georgia" to "Georgia, United States",
        "sydney" to "Council of the City of Sydney, New South Wales, Australia",
        "santiago" to "Santiago, Provincia de Santiago, Región Metropolitana de Santiago, Chile",
        "zurich" to "Zürich, Schweiz/Suisse/Svizzera/Svizra"
    )

     fun mapSearchTermToFullLocation(searchTerm: String): String? {
        val normalizedSearchTerm = searchTerm.lowercase().trim()
        return locationMap.entries.find { (key, value) ->
            key.contains(normalizedSearchTerm) || value.lowercase().contains(normalizedSearchTerm)
        }?.value
    }
}