package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.viewmodel.TaskViewModel

@Composable
fun TaskManagerScreen(
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    val tasks = viewModel.allTasks.collectAsState(initial = emptyList()).value
    val newTaskTitle = remember { mutableStateOf("") }

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

            // Add Task Input Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                TextField(
                    value = newTaskTitle.value,
                    onValueChange = { newTaskTitle.value = it },
                    label = { Text("Enter task title") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Button(
                    onClick = {
                        if (newTaskTitle.value.isNotEmpty()) {
                            viewModel.addTask(newTaskTitle.value)
                            newTaskTitle.value = ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                    Text("Add Task")
                }
            }

            // Task List Section
            if (tasks.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No tasks yet. Add one to get started!", fontSize = 16.sp)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(tasks) { task ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = task.title,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            },
                            leadingContent = {
                                Checkbox(
                                    checked = task.isCompleted,
                                    onCheckedChange = { isChecked ->
                                        viewModel.updateTask(task.copy(isCompleted = isChecked))
                                    }
                                )
                            },
                            trailingContent = {
                                IconButton(onClick = { viewModel.deleteTask(task.id) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
