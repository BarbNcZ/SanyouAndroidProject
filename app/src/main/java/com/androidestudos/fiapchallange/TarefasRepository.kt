package com.androidestudos.fiapchallange

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class TarefasRepository (
    private val dataSource: APIServerDataSource
) {

    fun createTarefa(
        cdTipoTarefa: Int,
        dsTarefas: String
    ) : Flow<CreateTarefaResult?> {

        return flow {

            emit(withContext(Dispatchers.IO) {
                dataSource.createTarefa(cdTipoTarefa, dsTarefas)

            })

        }

    }

}