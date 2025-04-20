package com.androidestudos.fiapchallange.ui.view.pages.menu

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

@Composable
fun MenuScreen(
    goToEmployeesScreen: () -> Unit,
    goToCreateTaskScreen: () -> Unit,
    goToDeleteTaskScreen: () -> Unit,
    goToDeleteFuncionarioScreen: () -> Unit
) {

    Button(onClick = {
        goToCreateTaskScreen()
    }) {
        Text("Criar Tarefa")
    }

    Button(onClick = {
        goToDeleteTaskScreen()
    }) {
        Text("Deletar Tarefa")
    }

    Button(onClick = {
        goToEmployeesScreen()
    }) {
        Text("Criar Funcionario")
    }

    Button(onClick = {
        goToDeleteFuncionarioScreen()
    }) {
        Text("Deletar Funcionario")
    }
}