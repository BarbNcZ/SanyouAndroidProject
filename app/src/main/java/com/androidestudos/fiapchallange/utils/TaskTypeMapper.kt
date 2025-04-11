package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

class TaskTypeMapper {
    fun fromGetTipoTarefaResultListToListOfGetTipoTarefaResult(
        getTipoTarefaResultList: Array<GetTipoTarefaResult>
    ): List<GetTipoTarefaResult> {
        return getTipoTarefaResultList.toList()
    }
}