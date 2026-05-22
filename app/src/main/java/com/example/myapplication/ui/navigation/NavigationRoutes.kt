package com.example.myapplication.ui.navigation

sealed class NavRoutes(val route: String) {
    data object TaskManager : NavRoutes("task_manager")
}
