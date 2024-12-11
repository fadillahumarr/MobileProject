package com.mobile.barugabaca.presentation.navigation

//Membuat routes untuk masing-masing screen
sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Login : Routes("login")
    object SignUp : Routes("signup")
    object Home : Routes("home")
    object Profile : Routes("profile")
    object RegistrationForm : Routes("registration_form")
    object BorrowBook : Routes("borrow_book")
    object DetailBook : Routes("detail_book")
    object DetailLibrary : Routes("detail_library")
}