package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.NavRoutes
import com.example.myapplication.ui.screens.AddTaskScreen
import com.example.myapplication.ui.screens.HomeScreen
import com.example.myapplication.ui.screens.ProfileScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                onNavigateToAddTask = {
                    navController.navigate(NavRoutes.AddTask.route)
                },
                onNavigateToProfile = {
                    navController.navigate(NavRoutes.Profile.route)
                }
            )
        }

        composable(NavRoutes.AddTask.route) {
            AddTaskScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onTaskAdded = { taskTitle ->
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.Profile.route) {
            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}