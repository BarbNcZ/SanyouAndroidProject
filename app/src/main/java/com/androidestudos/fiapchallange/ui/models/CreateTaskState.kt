package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

data class CreateTaskState(
    val taskId: Int = -1,
    val taskTypes: List<GetTipoTarefaResult> = emptyList(),
    val employees: List<GetFuncionarioResult> = emptyList()
)