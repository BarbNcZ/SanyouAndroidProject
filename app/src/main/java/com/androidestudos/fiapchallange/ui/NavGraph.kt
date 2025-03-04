package com.androidestudos.fiapchallange.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidestudos.fiapchallange.ui.view.pages.employees.EmployeesContainer
import com.androidestudos.fiapchallange.ui.view.pages.tarefas.TarefasContainer
import com.androidestudos.fiapchallange.utils.Constants

@Composable
fun NavGraph(
    title: MutableState<String> ,
    innerPaddings: PaddingValues ,
    navController: NavHostController,
    startDestination: String = Constants.Ui.TAREFAS_ROUTE_ID
){
    NavHost(navController, startDestination){
        composable(Route.Tarefas.route) {
            title.value = "Tarefas"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    TarefasContainer(navHostController = navController)
                }
            }

        }
        composable(Route.Employees.route) {
            title.value = "Funcionarios"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    EmployeesContainer()
                }
            }
        }
    }
}