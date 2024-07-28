package com.example.jetweatherforecast.screens.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(topBar = {}) {
Surface(modifier = Modifier.padding(it)) {
    Text(text = "Hello search")
}
    }
}