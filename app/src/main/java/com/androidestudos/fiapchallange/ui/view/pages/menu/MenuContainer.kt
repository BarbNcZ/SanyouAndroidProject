package com.androidestudos.fiapchallange.ui.view.pages.menu

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.Route
import com.androidestudos.fiapchallange.ui.viewmodel.MenuViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuContainer(
    viewModel: MenuViewModel = koinViewModel(),
    navHostController: NavHostController
){

    MenuScreen(
        goToEmployeesScreen = {
            navHostController.navigate(Route.Employees.route)
        },
        goToCreateTaskScreen = {
            navHostController.navigate(Route.CreateTarefa.route)
        },
        goToDeleteTaskScreen = {
            navHostController.navigate(Route.DeleteTarefa.route)
        },
        goToDeleteFuncionarioScreen = {
            navHostController.navigate(Route.DeleteFuncionario.route)
        }
    )
}