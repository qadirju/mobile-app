package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.Task
import com.example.myapplication.ui.components.TaskItem

@Composable
fun HomeScreen(
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tasks = remember { mutableStateOf(listOf<Task>()) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddTaskClick,
                icon = { Icon(Icons.Filled.Add, contentDescription = "Add Task") },
                text = { Text("Add Task") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "My Tasks",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (tasks.value.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Text("No tasks yet. Add one to get started!")
                }
            } else {
                LazyColumn {
                    items(tasks.value) { task ->
                        TaskItem(
                            task = task,
                            onTaskChecked = { isChecked ->
                                tasks.value = tasks.value.map {
                                    if (it.id == task.id) it.copy(isCompleted = isChecked) else it
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
