package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.DeleteFuncionarioResult
import com.androidestudos.fiapchallange.data.DeleteTarefasResult

class DeleteEmployeeMapper {
    fun fromDeleteFuncionarioResultToBoolean(
        deleteFuncionarioResult: DeleteFuncionarioResult?
    ): Boolean? {
        return deleteFuncionarioResult?.result
    }
}