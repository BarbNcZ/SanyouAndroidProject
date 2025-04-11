package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetDepartamentoResult

class DepartmentMapper {
    fun fromGetDepartamentoResultListToListOfGetDepartamentoResult(
        getDepartamentoResultList: Array<GetDepartamentoResult>
    ): List<GetDepartamentoResult> {
        return getDepartamentoResultList.toList()
    }
}