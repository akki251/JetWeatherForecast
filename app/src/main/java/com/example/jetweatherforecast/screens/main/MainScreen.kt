package com.example.jetweatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.model.WeatherInfo
import com.example.jetweatherforecast.model.WeatherItem
import com.example.jetweatherforecast.navigation.WeatherScreens
import com.example.jetweatherforecast.utils.AppColors
import com.example.jetweatherforecast.utils.AppFonts

import com.example.jetweatherforecast.utils.WeatherConditions
import com.example.jetweatherforecast.utils.getResource
import com.example.jetweatherforecast.utils.getTriPropertiesDay
import com.example.jetweatherforecast.utils.getWeekWeather

import com.example.jetweatherforecast.widgets.WeatherAppBar
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = "Seattle")

    }.value

    Log.d("MESSAGE", weatherData.e.toString())

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null)

        MainScaffold(weather = weatherData.data!!, navController)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceType")
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + ", " + weather.city.country,
            navController = navController,
 onAddActionClicked = {
     navController.navigate(WeatherScreens.SearchScreen.name)
 }

            ) {
            Log.d("CLICKED", "BUTTON CLICKED")
        }

    }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight(), color = AppColors.BASE_DARK
        ) {
            MainContent(data = weather, navController)
        }

    }

}

@SuppressLint("DiscouragedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(data: Weather, navController: NavController) {

    val currentDayData: WeatherItem? = data.list.firstOrNull()
    val weatherInfo: WeatherInfo = WeatherConditions.getGroupAndDescription(
        currentDayData?.weather?.firstOrNull()?.id ?: 0
    )




    Column(
        modifier = Modifier
            .padding(15.dp)
            .padding(horizontal = 0.dp),

        ) {

        MainWeatherHeader(data, weatherInfo)

        Spacer(modifier = Modifier.height(20.dp))

        AtmosphericContent(currentDayData)
        Spacer(modifier = Modifier.height(20.dp))

        Column( modifier = Modifier.verticalScroll(rememberScrollState()).padding(vertical = 20.dp), verticalArrangement = Arrangement.spacedBy(25.dp)) {

            getWeekWeather(data.list).forEach { it ->
                DayRow(dayTitle = it.weekDay, minTemp = it.minTemp, maxTemp = it.maxTemp, iconName = it.iconName)
            }
        }


    }

}

@Composable
private fun DayRow(dayTitle: String, minTemp: Int?, maxTemp: Int?, iconName: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        Text(
            modifier = Modifier.fillMaxWidth(.4f),
            text = dayTitle,
            fontFamily = AppFonts,
            color = AppColors.TEXT_LIGHT,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)

        ) {
            Text(
                text = minTemp.toString() + "\u00B0",
                fontFamily = AppFonts,
                color = AppColors.TEXT_DARK,
                fontWeight = FontWeight.SemiBold
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .height(3.dp)
                    .border(
                        width = 10.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color.DarkGray
                    )
            )
            Text(
                text = maxTemp.toString() + "\u00B0",
                fontFamily = AppFonts,
                color = AppColors.TEXT_LIGHT
            )
        }
        Image(

            modifier = Modifier.size(40.dp),
            painter = painterResource(id = getResource(iconName)),
            contentDescription = "clouds",
            contentScale = ContentScale.Fit

        )


    }
}

@Composable
private fun AtmosphericContent(currentDayData: WeatherItem?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(15.dp),
                clip = true
            )
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(15.dp))
                .background(AppColors.LIGHT_DARK)
                .fillMaxSize()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            getTriPropertiesDay(currentDayData).forEach { it ->
                AtmosphericDetailCard(title = it.title, value = it.value, iconName = it.iconName)
            }


        }
    }
}


@Composable
private fun AtmosphericDetailCard(title: String, value: String?, iconName: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(

            modifier = Modifier.size(40.dp),
            painter = painterResource(id = getResource(iconName)),
            contentDescription = "clouds",
            contentScale = ContentScale.Fit

        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = value!!,
            fontFamily = AppFonts,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TEXT_LIGHT,
            fontSize = 15.sp
        )

        Text(text = title, color = AppColors.TEXT_LIGHT, fontFamily = AppFonts, fontSize = 15.sp)
    }
}

@Composable
private fun MainWeatherHeader(
    data: Weather,
    weatherInfo: WeatherInfo
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = (data.list.firstOrNull()?.temp?.day.toString() + "\u00B0") ?: "",
                fontFamily = AppFonts,
                fontWeight = FontWeight.Bold,
                fontSize = 60.sp,
                color = AppColors.TEXT_LIGHT
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = weatherInfo.group ?: "",
                fontFamily = AppFonts,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = AppColors.TEXT_LIGHT
            )

        }
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = getResource(iconName = weatherInfo.iconName)),
            contentDescription = "clouds",
            contentScale = ContentScale.Fit


        )
    }
}



