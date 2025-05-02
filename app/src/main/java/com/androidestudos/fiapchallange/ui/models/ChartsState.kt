package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetChartTaskPerDepartmentResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerDifficultyResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerRoleResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList

data class ChartsState (
    val dataPointsPerEmployee: List<GetFuncionarioResult> = listOf(),
    val dataTasksPerRole: List<GetChartTaskPerRoleResult> = listOf(),
    val dataTasksPerDepartment: List<GetChartTaskPerDepartmentResult> = listOf(),
    val dataTasksPerDifficulty: List<GetChartTaskPerDifficultyResult> = listOf()
)