package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

data class TarefasState(
    val idTarefa: Int = -1,
    val idFuncionario: Int = -1,
    val tiposTarefa: List<GetTipoTarefaResult> = emptyList(),
    val cargos: List<GetCargoResult> = emptyList(),
    val funcionarios: List<GetFuncionarioResult> = emptyList(),
    val departamentos: List<GetDepartamentoResult> = emptyList(),
    val tarefas: List<GetTarefasResult> = emptyList(),
    val deleteResult: Boolean? = null
)
