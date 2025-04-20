package com.androidestudos.fiapchallange.ui.models

sealed class TasksEvents {

    data object TaskConcluded: TasksEvents()

    data object TaskNotConcluded: TasksEvents()

}