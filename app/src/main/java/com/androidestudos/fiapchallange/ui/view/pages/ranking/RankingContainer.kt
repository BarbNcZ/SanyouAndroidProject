package com.androidestudos.fiapchallange.ui.view.pages.ranking

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.viewmodel.RankingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RankingContainer(
    viewModel: RankingViewModel = koinViewModel(),
    navHostController: NavHostController
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    RankingScreen(
        state.value.funcionarios
    )
}