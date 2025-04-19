package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult

data class CreateEmployeesState(
    val employeeId: Int = -1,
    val roles: List<GetCargoResult> = emptyList(),
    val departments: List<GetDepartamentoResult> = emptyList(),
)