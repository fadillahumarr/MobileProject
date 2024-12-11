package com.mobile.barugabaca.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
)

fun getBottomNavigationItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            icon = Icons.Default.DateRange,
            route = Routes.BorrowBook.route
        ),
        BottomNavItem(
            icon = Icons.Default.Home,
            route = Routes.Home.route
        ),
        BottomNavItem(
            icon = Icons.Default.AccountCircle,
            route = Routes.Profile.route
        ),
    )
}
