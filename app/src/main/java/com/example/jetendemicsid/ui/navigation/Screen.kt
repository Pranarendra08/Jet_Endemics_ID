package com.example.jetendemicsid.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailHewan : Screen("home/{hewanId}") {
        fun createRoute(hewanId: Long) = "home/$hewanId"
    }
}
