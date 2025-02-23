package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

class TipoTarefaMapper {
    fun fromGetTipoTarefaResultListToListOfGetTipoTarefaResult(
        getTipoTarefaResultList: Array<GetTipoTarefaResult>
    ): List<GetTipoTarefaResult> {
        return getTipoTarefaResultList.toList()
    }
}