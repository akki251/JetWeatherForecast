package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetweatherforecast.utils.AppColors
import com.example.jetweatherforecast.utils.AppFonts

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController? = null,
    onAddActionClicked: () -> Unit = {},
    textAlign: TextAlign = TextAlign.Left,
    onButtonClicked: () -> Unit = {},

    ) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = AppColors.TEXT_LIGHT,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                textAlign = textAlign,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = AppFonts,



            )
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppColors.BASE_DARK,

            ),
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = AppColors.TEXT_LIGHT
                    )
                }
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AppColors.TEXT_LIGHT,
                    modifier = Modifier.clickable { onButtonClicked.invoke() })

            }
        }
    )
}

