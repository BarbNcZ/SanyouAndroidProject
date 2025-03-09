package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.data.GetTarefasResult

class TarefaMapper {
    fun fromGetTarefasResultListToListOfGetTarefasResult(
        getTarefasResultList: Array<GetTarefasResult>
    ): List<GetTarefasResult>{
        return getTarefasResultList.toList()
    }

    fun fromGetTarefaResultListToListOfGetTarefaResult(
        getTarefaResultList: Array<GetTarefaResult>
    ): List<GetTarefaResult>{
        return getTarefaResultList.toList()
    }
}