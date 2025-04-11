package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetFuncionarioResult

class EmployeeMapper {
    fun fromGetFuncionarioResultListToListOfGetFuncionarioResult(
        getFuncionarioResultList: Array<GetFuncionarioResult>
    ): List<GetFuncionarioResult> {
        return getFuncionarioResultList.toList()
    }
}