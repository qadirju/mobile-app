package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.TaskDao
import com.example.myapplication.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()

    fun getIncompleteTasks(): Flow<List<TaskEntity>> = taskDao.getIncompleteTasks()

    fun getCompletedTasks(): Flow<List<TaskEntity>> = taskDao.getCompletedTasks()

    suspend fun getTaskById(taskId: Int): TaskEntity? = taskDao.getTaskById(taskId)

    suspend fun insertTask(task: TaskEntity): Long = taskDao.insertTask(task)

    suspend fun updateTask(task: TaskEntity) = taskDao.updateTask(task)

    suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task)

    suspend fun deleteTaskById(taskId: Int) = taskDao.deleteTaskById(taskId)

    suspend fun deleteAllTasks() = taskDao.deleteAllTasks()
}
