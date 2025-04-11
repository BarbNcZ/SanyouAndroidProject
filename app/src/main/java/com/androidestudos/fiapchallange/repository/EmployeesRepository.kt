package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.CreateFuncionarioResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.network.APIServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class EmployeesRepository (
    private val dataSource: APIServerDataSource
) {
    fun createEmployee(
        cdDepto: Int,
        cdCargo: Int,
        dsEmail: String,
        nmFuncionario: String
    ) : Flow<CreateFuncionarioResult?> {

        return flow {

            emit(withContext(Dispatchers.IO) {
                dataSource.createFuncionario(cdDepto,cdCargo, dsEmail, nmFuncionario)

            })

        }

    }

    fun getEmployees() :Flow<GetFuncionarioResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getFuncionario()
                }
            )
        }
    }
}