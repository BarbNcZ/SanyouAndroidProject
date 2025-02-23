package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.DeleteTarefasResult

class DelTarefaMapper {
    fun fromDeleteTarefaResultToBoolean(
        deleteTarefasResult: DeleteTarefasResult?
    ): Boolean? {
        return deleteTarefasResult?.result
    }
}