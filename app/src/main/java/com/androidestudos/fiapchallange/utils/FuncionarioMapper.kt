package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResult

class FuncionarioMapper {
    fun fromGetFuncionarioResultListToListOfGetFuncionarioResult(
        getFuncionarioResultList: Array<GetFuncionarioResult>
    ): List<GetFuncionarioResult> {
        return getFuncionarioResultList.toList()
    }
}