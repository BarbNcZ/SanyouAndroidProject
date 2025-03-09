package com.androidestudos.fiapchallange.repository

import com.androidestudos.fiapchallange.data.CreateFuncionarioResult
import com.androidestudos.fiapchallange.network.APIServerDataSource
import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.data.DeleteTarefasResult
import com.androidestudos.fiapchallange.data.GetCargoResultList
import com.androidestudos.fiapchallange.data.GetDepartamentoResultList
import com.androidestudos.fiapchallange.data.GetFuncionarioResultList
import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.data.GetTarefaResultList
import com.androidestudos.fiapchallange.data.GetTarefasResultList
import com.androidestudos.fiapchallange.data.GetTipoTarefaResultList
import com.androidestudos.fiapchallange.data.LoginResult
import com.androidestudos.fiapchallange.data.User
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

    fun getTarefa(cdTarefa: Int) :Flow<GetTarefaResult?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTarefa(cdTarefa)
                }
            )
        }
    }

    fun getTarefasByFuncionario(cdFuncionario: Int) :Flow<GetTarefaResultList?> {

        return flow {

            emit(
                withContext(Dispatchers.IO) {
                    dataSource.getTarefaByFuncionario(cdFuncionario)
                }
            )
        }
    }

    fun login(user: User) :Flow<LoginResult?> {

        return flow {
            emit(
                withContext(Dispatchers.IO) {
                    dataSource.login(user)
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