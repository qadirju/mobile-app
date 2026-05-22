package com.example.myapplication.ui.navigation

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object AddTask : NavRoutes("add_task")
    data object Profile : NavRoutes("profile")
}
