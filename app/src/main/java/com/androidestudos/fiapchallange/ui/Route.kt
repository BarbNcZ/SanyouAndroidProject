package com.androidestudos.fiapchallange.ui

import com.androidestudos.fiapchallange.BuildConfig
import com.androidestudos.fiapchallange.utils.Constants

enum class Route (
    val routeId: String,
    val showBackButton: Boolean,
    val showMenu: Boolean
) {
    LOGIN(
        routeId = Constants.Ui.LOGIN_ROUTE_ID,
        showBackButton = false,
        showMenu = BuildConfig.DEBUG
    ),
    MENU(
        routeId = Constants.Ui.MENU_ROUTE_ID,
        showBackButton = false,
        showMenu = true
    ),
    TASKS(
        routeId = Constants.Ui.TASKS_ROUTE_ID,
        showBackButton = false,
        showMenu = true
    ),
    RANKING(
        routeId = Constants.Ui.RANKING_ROUTE_ID,
        showBackButton = true,
        showMenu = true
    ),
    CHARTS_SAMPLES(
        routeId = Constants.Ui.CHARTS_SAMPLES_ROUTE_ID,
        showBackButton = true,
        showMenu = false
    ),
    CREATE_TASK(
        routeId = Constants.Ui.CREATE_TASK_ROUTE_ID,
        showBackButton = true,
        showMenu = true
    ),
    DELETE_TASK(
        routeId = Constants.Ui.DELETE_TASK_ROUTE_ID,
        showBackButton = true,
        showMenu = true
    ),
    EMPLOYEES(
        routeId = Constants.Ui.EMPLOYEES_ROUTE_ID,
        showBackButton = true,
        showMenu = true
    ),
    DELETE_EMPLOYEE(
        routeId = Constants.Ui.DELETE_EMPLOYEE_ROUTE_ID,
        showBackButton = true,
        showMenu = true
    ),
    CHARTS(
        routeId = Constants.Ui.CHARTS_ROUTE_ID,
        showBackButton = true,
        showMenu = true
    )
}