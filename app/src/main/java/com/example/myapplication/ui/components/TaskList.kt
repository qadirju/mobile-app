package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskList(
    tasks: List<Task>,
    onTaskToggle: (Int, Boolean) -> Unit,
    onTaskDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (tasks.isEmpty()) {
        Column(
            modifier = modifier
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
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = tasks,
                key = { task -> task.id }
            ) { task ->
                TaskItem(
                    task = task,
                    onTaskChecked = { isChecked ->
                        onTaskToggle(task.id, isChecked)
                    },
                    onTaskDelete = {
                        onTaskDelete(task.id)
                    }
                )
            }
        }
    }
}
