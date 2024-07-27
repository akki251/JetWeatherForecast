package com.example.jetweatherforecast.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import javax.inject.Singleton


    @SuppressLint("DiscouragedApi")
    @Composable
 public fun  getResource (iconName : String) : Int {


        val resourceId = LocalContext.current.resources.getIdentifier(
            iconName,
            "drawable",
            LocalContext.current.packageName
        )

        return  resourceId
    }

