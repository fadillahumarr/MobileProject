package com.mobile.barugabaca.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.barugabaca.presentation.borrowbook.BorrowBookScreen
import com.mobile.barugabaca.presentation.detailbook.DetailBookScreen
import com.mobile.barugabaca.presentation.home.HomeScreen
import com.mobile.barugabaca.presentation.library.DetailLibraryScreen
import com.mobile.barugabaca.presentation.login.LoginScreen
import com.mobile.barugabaca.presentation.profile.ProfileScreen
import com.mobile.barugabaca.presentation.registrationform.RegistrationFormScreen
import com.mobile.barugabaca.presentation.signup.SignUpScreen
import com.mobile.barugabaca.presentation.splash.SplashScreen

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController();
    Scaffold(
        bottomBar = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
                    Routes.Home.route, Routes.BorrowBook.route, Routes.Profile.route
                )
            ) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Splash.route) {
                SplashScreen(navController)
            }
            composable(Routes.Login.route) {
                LoginScreen(navController)
            }
            composable(Routes.SignUp.route) {
                SignUpScreen(navController)
            }
            composable(Routes.Home.route) {
                HomeScreen(navController)
            }
            composable(Routes.BorrowBook.route) {
                BorrowBookScreen(navController)
            }
            composable(Routes.Profile.route) {
                ProfileScreen(navController)
            }
            composable(Routes.RegistrationForm.route) {
                RegistrationFormScreen(navController)
            }
            composable("${Routes.DetailLibrary.route}/{libraryId}") { backStackEntry ->
                val libraryId = backStackEntry.arguments?.getString("libraryId") ?: ""
                DetailLibraryScreen(navController, libraryId)
            }
            composable("${Routes.DetailBook.route}/{libraryId}/{bookId}") { backStackEntry ->
                val libraryId = backStackEntry.arguments?.getString("libraryId") ?: ""
                val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                DetailBookScreen(
                    navController = navController,
                    libraryId = libraryId,
                    bookId = bookId
                )
            }
        }
    }
}