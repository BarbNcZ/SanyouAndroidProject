package com.androidestudos.fiapchallange.ui

import com.androidestudos.fiapchallange.utils.Constants

sealed class Route(val route: String) {
    data object Tarefas: Route(Constants.Ui.TAREFAS_ROUTE_ID)
    data object Employees: Route(Constants.Ui.EMPLOYEES_ROUTE_ID)
}