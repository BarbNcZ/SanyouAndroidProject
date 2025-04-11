package com.androidestudos.fiapchallange.ui.view.pages.createTarefa

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.viewmodel.CreateTarefaViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateTaskContainer(
    viewModel: CreateTarefaViewModel = koinViewModel(),
    navHostController: NavHostController
){

    val state = viewModel.state.collectAsStateWithLifecycle()

    CreateTaskScreen(
        taskId = state.value.taskId,
        taskTypes = state.value.taskTypes,
        createTask = viewModel::createTask,
        employees = state.value.employees
    )
}