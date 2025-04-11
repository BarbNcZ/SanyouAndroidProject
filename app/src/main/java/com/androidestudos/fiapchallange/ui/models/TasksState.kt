package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetTarefaResult

data class TasksState(
    val tasksByEmployee: List<GetTarefaResult> = emptyList(),
)