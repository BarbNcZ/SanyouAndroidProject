package com.androidestudos.fiapchallange.ui.view.pages.tarefa

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.viewmodel.TarefaViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskContainer(
    viewModel: TarefaViewModel = koinViewModel(),
    navHostController: NavHostController,

    ){

    val state = viewModel.state.collectAsStateWithLifecycle()


    TaskScreen(
        ""
    )
}