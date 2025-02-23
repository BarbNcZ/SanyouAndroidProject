package com.androidestudos.fiapchallange.ui.view

import androidx.compose.runtime.Composable
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

@Composable
fun TarefasScreen(

    idTarefa: Int,
    tiposTarefa: List<GetTipoTarefaResult>,
    tarefas: List<GetTarefasResult>,
    criarTarefa: (Int, String) -> Unit,
    deleteTarefa: (Int) -> Unit
) {

    CreateTarefasScreen(
        criarTarefa,
        idTarefa,
        tiposTarefa
    )

    DeleteTarefaScreen(
        deleteTarefa,
        tarefas
    )

}
