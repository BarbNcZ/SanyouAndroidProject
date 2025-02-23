package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetDepartamentoResult

class DepartamentoMapper {
    fun fromGetDepartamentoResultListToListOfGetDepartamentoResult(
        getDepartamentoResultList: Array<GetDepartamentoResult>
    ): List<GetDepartamentoResult> {
        return getDepartamentoResultList.toList()
    }
}