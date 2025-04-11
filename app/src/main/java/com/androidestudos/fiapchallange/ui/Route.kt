package com.androidestudos.fiapchallange.ui

import com.androidestudos.fiapchallange.utils.Constants

sealed class Route(val route: String) {
    data object Tarefas: Route(Constants.Ui.TASKS_ROUTE_ID)
    data object CreateTarefa: Route(Constants.Ui.CREATE_TASK_ROUTE_ID)
    data object DeleteTarefa: Route(Constants.Ui.DELETE_TASK_ROUTE_ID)
    data object Employees: Route(Constants.Ui.EMPLOYEES_ROUTE_ID)
    data object Login: Route(Constants.Ui.LOGIN_ROUTE_ID)
    data object Menu: Route(Constants.Ui.MENU_ROUTE_ID)
    data object Ranking: Route(Constants.Ui.RANKING_ROUTE_ID)
}