package com.mobile.barugabaca.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.mobile.barugabaca.presentation.navigation.MyAppNavigation
import com.mobile.barugabaca.ui.theme.BarugaBacaTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BarugaBacaTheme {
                // Navigasi utama aplikasi
                MyAppNavigation()
            }
        }
    }
}
