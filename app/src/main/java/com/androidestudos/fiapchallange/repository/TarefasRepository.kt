package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.CreateFuncionarioResult
import com.androidestudos.fiapchallange.network.APIServerDataSource
import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.data.DeleteTarefasResult
import com.androidestudos.fiapchallange.data.GetCargoResultList
import com.androidestudos.fiapchallange.data.GetDepartamentoResultList
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.data.GetTarefasResultList
import com.androidestudos.fiapchallange.data.GetTipoTarefaResultList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class TarefasRepository (
    private val dataSource: APIServerDataSource
) {

    fun createTarefa(
        cdTipoTarefa: Int,
        cdFuncionario: Int,
        dsTarefas: String
    ) : Flow<CreateTarefaResult?> {

        return flow {

            emit(withContext(Dispatchers.IO) {
                dataSource.createTarefa(cdTipoTarefa, cdFuncionario , dsTarefas)

            })

        }

    }

    fun getTipoTarefa() :Flow<GetTipoTarefaResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTipoTarefa()
                }
            )
        }
    }

    fun getTarefas() :Flow<GetTarefasResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTarefas()
                }
            )
        }
    }

    fun deleteTarefa(
        cdTarefa: Int
    ) : Flow<DeleteTarefasResult?> {

        return flow {

            emit(withContext(Dispatchers.IO) {
                dataSource.deleteTarefa(cdTarefa)

            })

        }

    }


    fun createFuncionario(
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


    fun getDepartamento() :Flow<GetDepartamentoResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getDepartamento()
                }
            )
        }
    }

    fun getCargo() :Flow<GetCargoResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getCargo()
                }
            )
        }
    }

    fun getFuncionarios() :Flow<GetFuncionarioResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getFuncionario()
                }
            )
        }
    }
}