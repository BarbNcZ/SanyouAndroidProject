package com.androidestudos.fiapchallange.ui.view.pages.charts

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.viewmodel.ChartsViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.TarefaViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChartsContainer(
    viewModel: ChartsViewModel = koinViewModel(),
    navHostController: NavHostController,

    ){

    val state = viewModel.state.collectAsStateWithLifecycle()

    ChartsScreen(
        state.value.dataPointsPerEmployee,
        state.value.dataTasksPerRole,
        state.value.dataTasksPerDepartment,
        state.value.dataTasksPerDifficulty
    )
}