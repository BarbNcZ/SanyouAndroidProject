package com.androidestudos.fiapchallange.ui.view.pages.menu

import androidx.compose.runtime.Composable
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
            navHostController.navigate(Route.EMPLOYEES.routeId)
        },
        goToCreateTaskScreen = {
            navHostController.navigate(Route.CREATE_TASK.routeId)
        },
        goToDeleteTaskScreen = {
            navHostController.navigate(Route.DELETE_TASK.routeId)
        },
        goToDeleteFuncionarioScreen = {
            navHostController.navigate(Route.DELETE_EMPLOYEE.routeId)
        }
    )
}