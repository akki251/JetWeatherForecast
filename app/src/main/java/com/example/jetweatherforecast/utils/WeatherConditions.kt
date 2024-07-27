package com.example.jetweatherforecast.utils

import com.example.jetweatherforecast.model.WeatherInfo

enum class WeatherConditions(val groupName: String, val description: String) {
    THUNDERSTORM("Thunderstorm", "Thunderstorm conditions"), DRIZZLE(
        "Drizzle",
        "Drizzle conditions"
    ),
    RAIN("Rain", "Rain conditions"), SNOW("Snow", "Snow conditions"), ATMOSPHERE(
        "Atmosphere",
        "Atmospheric conditions"
    ),
    CLEAR("Clear", "Clear sky"), CLOUDS("Clouds", "Cloudy conditions");

    companion object {

        fun getGroupAndDescription(code: Int): WeatherInfo {
            return when (code / 100) {
                2 -> WeatherInfo(
                    THUNDERSTORM.groupName, THUNDERSTORM.description, "thunderstorm.png"
                )

                3 -> WeatherInfo(DRIZZLE.groupName, DRIZZLE.description, "drizzle.png")
                5 -> WeatherInfo(RAIN.groupName, RAIN.description, "rain.png")
                6 -> WeatherInfo(SNOW.groupName, SNOW.description, "snow.png")
                7 -> WeatherInfo(ATMOSPHERE.groupName, ATMOSPHERE.description, "atmosphere.png")
                8 -> WeatherInfo(CLOUDS.groupName, CLOUDS.description, "clouds")
                else -> WeatherInfo("Unknown", "Unknown code", "unknown.png")
            }
        }
    }
}

