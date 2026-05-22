package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for Task
data class TaskItem(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
)

@Composable
fun TaskManagerScreen(
    modifier: Modifier = Modifier
) {
    // State to store the input text field value
    val taskInputState = remember { mutableStateOf("") }
    
    // State to store the list of tasks using mutableStateListOf
    val tasks = remember { mutableStateListOf<TaskItem>() }
    
    // Counter for generating unique task IDs
    val taskIdCounter = remember { mutableStateOf(0) }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Header
            Text(
                text = "Student Task Manager",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Task Input Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = taskInputState.value,
                    onValueChange = { taskInputState.value = it },
                    label = { Text("Enter task title") },
                    modifier = Modifier
                        .weight(1f),
                    singleLine = true
                )

                Button(
                    onClick = {
                        if (taskInputState.value.isNotEmpty()) {
                            // Add new task to the list
                            tasks.add(
                                TaskItem(
                                    id = taskIdCounter.value,
                                    title = taskInputState.value,
                                    isCompleted = false
                                )
                            )
                            taskIdCounter.value++
                            // Clear the input field
                            taskInputState.value = ""
                        }
                    },
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text("Add Task")
                }
            }

            // Tasks List Section
            if (tasks.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "No tasks yet. Add one to get started!",
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = tasks,
                        key = { task -> task.id }
                    ) { task ->
                        TaskRow(
                            task = task,
                            onTaskCheckedChange = { isChecked ->
                                val index = tasks.indexOfFirst { it.id == task.id }
                                if (index != -1) {
                                    tasks[index] = task.copy(isCompleted = isChecked)
                                }
                            },
                            onDeleteTask = {
                                tasks.removeAll { it.id == task.id }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskRow(
    task: TaskItem,
    onTaskCheckedChange: (Boolean) -> Unit,
    onDeleteTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Checkbox and Title
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onTaskCheckedChange
                )

                Text(
                    text = task.title,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp,
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            }

            // Delete Button
            IconButton(
                onClick = onDeleteTask,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Task"
                )
            }
        }
    }
}
