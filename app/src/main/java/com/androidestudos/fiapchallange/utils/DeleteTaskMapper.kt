package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.DeleteTarefasResult

class DeleteTaskMapper {
    fun fromDeleteTarefaResultToBoolean(
        deleteTarefasResult: DeleteTarefasResult?
    ): Boolean? {
        return deleteTarefasResult?.result
    }
}