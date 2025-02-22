package com.example.jetweatherforecast.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweatherforecast.screens.main.MainScreen
import com.example.jetweatherforecast.screens.main.MainViewModel
import com.example.jetweatherforecast.screens.search.SearchScreen
import com.example.jetweatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        composable(WeatherScreens.MainScreen.name) {

            val mainViewModel = hiltViewModel<MainViewModel>()


            MainScreen(navController = navController, mainViewModel)
        }
        composable(WeatherScreens.SearchScreen.name ,    enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left , animationSpec = tween(500))
        },
            exitTransition = {

                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right , animationSpec = tween(500))
            } ) {


            SearchScreen(navController = navController)
        }
    }

}