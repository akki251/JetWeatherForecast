package com.example.jetweatherforecast.utils


import com.example.jetweatherforecast.model.WeatherItem

import kotlin.math.roundToInt

data class Atmospheric(val title: String, val value: String?, val iconName: String)


public fun getTriPropertiesDay(currentDayData: WeatherItem?): List<Atmospheric> {


    val atmosphericProperties = listOf<Atmospheric>(
        Atmospheric(
            title = "Wind",
            value = currentDayData?.speed.toString() + " m/s",
            iconName = "wind"
        ),
        Atmospheric(
            title = "Humidity",
            value = currentDayData?.humidity.toString() + " %",
            iconName = "wind"
        ),
        Atmospheric(
            title = "Wind",
            value = (currentDayData?.rain?.times(100))?.roundToInt().toString() + " %",
            iconName =  "wind"

        ),

    )

    return atmosphericProperties

}