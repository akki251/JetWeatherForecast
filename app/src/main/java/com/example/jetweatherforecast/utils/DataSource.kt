package com.example.jetweatherforecast.utils


import com.example.jetweatherforecast.model.WeatherItem
import java.time.LocalDate
import javax.inject.Singleton

import kotlin.math.roundToInt
@Singleton
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

@Singleton
data class DayData(val weekDay : String, val minTemp : Int?, val maxTemp : Int?, val iconName : String)

public  fun getWeekWeather(weekWeatherData  : List<WeatherItem>)  : List<DayData> {




    val todayDate = LocalDate.now();
    val dayNames = mutableListOf<DayData>()

    for(i in 0..6) {
val upcomingDate = todayDate.plusDays(i.toLong());
        val dayName = upcomingDate.dayOfWeek.toString().lowercase().replaceFirstChar(Char::titlecase)
       val minTemp = weekWeatherData[i].temp.min.toInt()
        val maxTemp = weekWeatherData[i].temp.max.toInt()
        val dayData = DayData(weekDay = if (i == 0) "Today" else dayName  , minTemp , maxTemp , iconName = WeatherConditions.getGroupAndDescription(weekWeatherData[i].weather[0].id).iconName)
        dayNames.add(dayData)
    }

    return dayNames

}


