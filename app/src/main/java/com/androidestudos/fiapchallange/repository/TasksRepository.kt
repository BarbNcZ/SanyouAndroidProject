package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.data.DeleteTarefasResult
import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.data.GetTarefaResultList
import com.androidestudos.fiapchallange.data.GetTarefasResultList
import com.androidestudos.fiapchallange.data.GetTipoTarefaResultList
import com.androidestudos.fiapchallange.network.APIServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class TasksRepository (
    private val dataSource: APIServerDataSource
) {

    fun createTask(
        taskTypeId: Int,
        employeeId: Int,
        task: String,
        estimation: Int,
        time: Long
    ) : Flow<CreateTarefaResult?> {

        return flow {

            emit(withContext(Dispatchers.IO) {
                dataSource.createTarefa(taskTypeId, employeeId , task, estimation, time)

            })

        }

    }

    fun getTaskType() :Flow<GetTipoTarefaResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTipoTarefa()
                }
            )
        }
    }

    fun getTasks() :Flow<GetTarefasResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTarefas()
                }
            )
        }
    }

    fun getTask(cdTarefa: Int) :Flow<GetTarefaResult?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTarefa(cdTarefa)
                }
            )
        }
    }

    fun getTasksByEmployee(cdFuncionario: Int) :Flow<GetTarefaResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTarefaByFuncionario(cdFuncionario)
                }
            )
        }
    }

    fun deleteTask(
        cdTarefa: Int
    ) : Flow<DeleteTarefasResult?> {

        return flow {

            emit(withContext(Dispatchers.IO) {
                dataSource.deleteTarefa(cdTarefa)

            })

        }

    }
}