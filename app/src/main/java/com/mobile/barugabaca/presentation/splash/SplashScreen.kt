package com.mobile.barugabaca.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.barugabaca.presentation.navigation.Routes
import com.mobile.barugabaca.ui.theme.BarugaBacaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {
    //Menambahkan Delay
    CoroutineScope(Dispatchers.Main).launch{
        delay(3000L)
        navController.navigate(Routes.Login.route)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    BarugaBacaTheme {
        SplashScreen(navController = rememberNavController())
    }
}
