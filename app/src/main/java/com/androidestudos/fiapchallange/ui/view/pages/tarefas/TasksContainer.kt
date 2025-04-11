package com.androidestudos.fiapchallange.ui.view.pages.tarefas

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TasksContainer(
    viewModel: TarefasViewModel = koinViewModel(),
    navHostController: NavHostController,
    cdFuncionario: Int? = null
){

    viewModel.getTarefasByFuncionario(cdFuncionario ?: -1)

    val state = viewModel.state.collectAsStateWithLifecycle()

    TasksScreen(
        state.value.tasksByEmployee
    )
}