package com.androidestudos.fiapchallange.ui.view.pages.tarefas

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult
import com.androidestudos.fiapchallange.ui.view.pages.CreateTarefasScreen
import com.androidestudos.fiapchallange.ui.view.pages.DeleteTarefaScreen

@Composable
fun TarefasScreen(
    goToEmployeesScreen: () -> Unit,
    idTarefa: Int,
    tiposTarefa: List<GetTipoTarefaResult>,
    tarefas: List<GetTarefasResult>,
    criarTarefa: (Int, Int, String) -> Unit,
    deleteTarefa: (Int) -> Unit,
    funcionarios: List<GetFuncionarioResult>
) {

    CreateTarefasScreen(
        criarTarefa,
        idTarefa,
        tiposTarefa,
        funcionarios
    )

    DeleteTarefaScreen(
        deleteTarefa,
        tarefas
    )

    Button(onClick = {
        goToEmployeesScreen()
    }) {
        Text("Criar Funcionario")
    }

}
