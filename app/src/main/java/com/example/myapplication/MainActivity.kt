package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.repository.TaskRepository
import com.example.myapplication.ui.navigation.NavRoutes
import com.example.myapplication.ui.screens.TaskManagerScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.viewmodel.TaskViewModel
import com.example.myapplication.ui.viewmodel.TaskViewModelFactory

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
    val context = LocalContext.current

    val viewModelFactory = remember(context) {
        val database = AppDatabase.getInstance(context)
        val repository = TaskRepository(database.taskDao())
        TaskViewModelFactory(repository)
    }

    NavHost(
        navController = navController,
        startDestination = NavRoutes.TaskManager.route
    ) {
        composable(NavRoutes.TaskManager.route) {
            val taskViewModel: TaskViewModel = viewModel(factory = viewModelFactory)
            TaskManagerScreen(viewModel = taskViewModel)
        }
    }
}
