package com.androidestudos.fiapchallange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidestudos.fiapchallange.ui.view.pages.ChartsSamplesScreen
import com.androidestudos.fiapchallange.ui.view.pages.charts.ChartsContainer
import com.androidestudos.fiapchallange.ui.view.pages.createTarefa.CreateTaskContainer
import com.androidestudos.fiapchallange.ui.view.pages.createemployees.CreateEmployeeContainer
import com.androidestudos.fiapchallange.ui.view.pages.deleteFuncionario.DeleteFuncionarioContainer
import com.androidestudos.fiapchallange.ui.view.pages.deleteTarefa.DeleteTaskContainer
import com.androidestudos.fiapchallange.ui.view.pages.login.LoginContainer
import com.androidestudos.fiapchallange.ui.view.pages.menu.MenuContainer
import com.androidestudos.fiapchallange.ui.view.pages.ranking.RankingContainer
import com.androidestudos.fiapchallange.ui.view.pages.tarefas.TasksContainer
import com.androidestudos.fiapchallange.utils.Constants

@Composable
fun NavGraph(
    title: MutableState<String> ,
    innerPaddings: PaddingValues ,
    navController: NavHostController,
    startDestination: String = Constants.Ui.LOGIN_ROUTE_ID,
    isManager: MutableState<Boolean>,
    //startDestination: String = Constants.Ui.CREATE_TASK_ROUTE_ID
){
    NavHost(navController, startDestination){
        composable(

            StringBuilder().apply {
                append("${Route.TASKS.routeId}/")
                append(
                    "{${Constants.Ui.TASKS_EMPLOYEE_ID_ARGUMENT}}"
                )
            }.toString(),
            listOf(navArgument(Constants.Ui.TASKS_EMPLOYEE_ID_ARGUMENT){type = NavType.IntType})


        ) { stackEntry ->
            val cdFuncionario = stackEntry.arguments?.getInt(Constants.Ui.TASKS_EMPLOYEE_ID_ARGUMENT)
            title.value = "Tarefas"
            Column(modifier = Modifier.padding(innerPaddings)) {
                TasksContainer(navHostController = navController, cdFuncionario = cdFuncionario)
            }
        }

        composable(Route.EMPLOYEES.routeId) {
            title.value = "Funcionarios"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    CreateEmployeeContainer()
                }
            }
        }

        composable(Route.RANKING.routeId) {
            title.value = "Ranking"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    RankingContainer(navHostController = navController)
                }
            }
        }

        composable(Route.CHARTS_SAMPLES.routeId) {
            title.value = "Charts Samples"
            Column(modifier = Modifier.padding(innerPaddings)) {
                ChartsSamplesScreen()
            }
        }

        composable(Route.MENU.routeId) {
            title.value = "Menu"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    MenuContainer(navHostController = navController)
                }
            }
        }

        composable(Route.CREATE_TASK.routeId) {
            title.value = "Criar Tarefa"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    CreateTaskContainer(navHostController = navController)
                }
            }
        }

        composable(Route.DELETE_TASK.routeId) {
            title.value = "Deletar Tarefa"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    DeleteTaskContainer(navHostController = navController)
                }
            }
        }

        composable(Route.DELETE_EMPLOYEE.routeId) {
            title.value = "Deletar Funcionario"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    DeleteFuncionarioContainer(navHostController = navController)
                }
            }
        }

        composable(Route.LOGIN.routeId) {
            title.value = "Login"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    LoginContainer(navHostController = navController, isManager = isManager)
                }
            }
        }

        composable(Route.CHARTS.routeId) {
            title.value = "Graficos"
            Column(modifier = Modifier.padding(innerPaddings)) {
                ChartsContainer(navHostController = navController)
            }
        }
    }
}



