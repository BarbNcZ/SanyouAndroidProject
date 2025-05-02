package com.androidestudos.fiapchallange.utils

import com.androidestudos.fiapchallange.data.GetChartTaskPerDepartmentResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerDifficultyResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerRoleResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResult

class ChartsMapper {
    fun fromGetChartTaskPerRoleResultListToListOfGetChartTaskPerRoleResult(
        getChartTaskPerRoleResultList: Array<GetChartTaskPerRoleResult>
    ): List<GetChartTaskPerRoleResult> {
        return getChartTaskPerRoleResultList.toList()
    }
    fun fromGetChartTaskPerDepartmentResultListToListOfGetChartTaskPerDepartmentResult(
        getChartTaskPerDepartmentResultList: Array<GetChartTaskPerDepartmentResult>
    ): List<GetChartTaskPerDepartmentResult> {
        return getChartTaskPerDepartmentResultList.toList()
    }
    fun fromGetChartTaskPerDifficultyResultListToListOfGetChartTaskPerDifficultyResult(
        getChartTaskPerDifficultyResultList: Array<GetChartTaskPerDifficultyResult>
    ): List<GetChartTaskPerDifficultyResult> {
        return getChartTaskPerDifficultyResultList.toList()
    }
}