package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.AddTaskSection
import com.example.myapplication.ui.components.Task
import com.example.myapplication.ui.components.TaskList

@Composable
fun HomeScreen(
    onNavigateToAddTask: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State management - hoisted to top level
    val tasks = remember { mutableStateListOf<Task>() }
    val taskInputState = remember { mutableStateOf("") }
    val taskIdCounter = remember { mutableStateOf(0) }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Header with Navigation Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Student Task Manager",
                    fontSize = 24.sp
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = onNavigateToProfile) {
                        Text("Profile")
                    }
                }
            }

            // Add Task Section
            AddTaskSection(
                inputValue = taskInputState.value,
                onInputChange = { taskInputState.value = it },
                onAddClick = {
                    if (taskInputState.value.isNotEmpty()) {
                        tasks.add(
                            Task(
                                id = taskIdCounter.value,
                                title = taskInputState.value,
                                isCompleted = false
                            )
                        )
                        taskIdCounter.value++
                        taskInputState.value = ""
                    }
                }
            )

            // Navigate to Add Task Screen Button
            Button(
                onClick = onNavigateToAddTask,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Add Task (Navigation)")
            }

            // Task List Section
            TaskList(
                tasks = tasks,
                onTaskToggle = { taskId, isCompleted ->
                    val index = tasks.indexOfFirst { it.id == taskId }
                    if (index != -1) {
                        tasks[index] = tasks[index].copy(isCompleted = isCompleted)
                    }
                },
                onTaskDelete = { taskId ->
                    tasks.removeAll { it.id == taskId }
                }
            )
        }
    }
}

