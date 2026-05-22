package com.example.myapplication.ui.screens

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.CustomButton
import com.example.myapplication.ui.components.TaskTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onBackClick: () -> Unit,
    onSaveTask: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val titleState = remember { mutableStateOf("") }
    val descriptionState = remember { mutableStateOf("") }
    val imageState = remember { mutableStateOf<Bitmap?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            imageState.value = bitmap
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add New Task") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image Section
            if (imageState.value != null) {
                Image(
                    bitmap = imageState.value!!.asImageBitmap(),
                    contentDescription = "Task Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                        .clickable { cameraLauncher.launch(null) },
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable { cameraLauncher.launch(null) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = "Camera",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text("Tap to add image")
                    }
                }
            }

            // Title TextField
            TaskTextField(
                value = titleState.value,
                onValueChange = { titleState.value = it },
                label = "Task Title",
                placeholder = "Enter task title"
            )

            // Description TextField
            TaskTextField(
                value = descriptionState.value,
                onValueChange = { descriptionState.value = it },
                label = "Task Description",
                placeholder = "Enter task description",
                maxLines = 4
            )

            // Save Button
            CustomButton(
                text = "Save Task",
                onClick = {
                    if (titleState.value.isNotEmpty()) {
                        onSaveTask(titleState.value, descriptionState.value)
                        titleState.value = ""
                        descriptionState.value = ""
                        imageState.value = null
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            )

            // Cancel Button
            CustomButton(
                text = "Cancel",
                onClick = onBackClick,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
