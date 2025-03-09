package com.androidestudos.fiapchallange.ui

import com.androidestudos.fiapchallange.utils.Constants

sealed class Route(val route: String) {
    data object Tarefas: Route(Constants.Ui.TAREFAS_ROUTE_ID)
    data object CreateDeleteTarefa: Route(Constants.Ui.CREATE_DELETE_TAREFA_ROUTE_ID)
    data object Employees: Route(Constants.Ui.EMPLOYEES_ROUTE_ID)
    data object Login: Route(Constants.Ui.LOGIN_ROUTE_ID)
}