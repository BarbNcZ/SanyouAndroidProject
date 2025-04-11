package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.GetDepartamentoResultList
import com.androidestudos.fiapchallange.network.APIServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class DepartmentsRepository (
    private val dataSource: APIServerDataSource
) {
    fun getDepartment() :Flow<GetDepartamentoResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getDepartamento()
                }
            )
        }
    }
}