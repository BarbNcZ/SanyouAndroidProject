package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.GetChartTaskPerDepartmentResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerDepartmentResultList
import com.androidestudos.fiapchallange.data.GetChartTaskPerDifficultyResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerDifficultyResultList
import com.androidestudos.fiapchallange.data.GetChartTaskPerRoleResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerRoleResultList
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.network.APIServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ChartsRepository(
    private val dataSource: APIServerDataSource
) {

    fun getChartTaskPerRole() :Flow<GetChartTaskPerRoleResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getChartTaskPerRole()
                }
            )
        }
    }
    fun getChartTaskPerDepartment() :Flow<GetChartTaskPerDepartmentResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getChartTaskPerDepartment()
                }
            )
        }
    }
    fun getChartTaskPerDifficulty() :Flow<GetChartTaskPerDifficultyResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getChartTaskPerDifficulty()
                }
            )
        }
    }
}