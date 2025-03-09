package com.androidestudos.fiapchallange.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidestudos.fiapchallange.ui.view.pages.createDeleteTarefa.CreateDeleteTarefaContainer
import com.androidestudos.fiapchallange.ui.view.pages.employees.EmployeesContainer
import com.androidestudos.fiapchallange.ui.view.pages.login.LoginContainer
import com.androidestudos.fiapchallange.ui.view.pages.tarefas.TarefasContainer
import com.androidestudos.fiapchallange.utils.Constants

@Composable
fun NavGraph(
    title: MutableState<String> ,
    innerPaddings: PaddingValues ,
    navController: NavHostController,
    startDestination: String = Constants.Ui.LOGIN_ROUTE_ID
){
    NavHost(navController, startDestination){
        composable(

            StringBuilder().apply {
                append("${Route.Tarefas.route}/")
                append(
                    "{${Constants.Ui.TAREFAS_CD_FUNCIONARIO_ARGUMENT}}"
                )
            }.toString(),
            listOf(navArgument(Constants.Ui.TAREFAS_CD_FUNCIONARIO_ARGUMENT){type = NavType.IntType})


        ) { stackEntry ->
            val cdFuncionario = stackEntry.arguments?.getInt(Constants.Ui.TAREFAS_CD_FUNCIONARIO_ARGUMENT)
            title.value = "Tarefas"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    TarefasContainer(navHostController = navController, cdFuncionario = cdFuncionario)
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

        composable(Route.CreateDeleteTarefa.route) {
            title.value = "Create or Delete Tarefa"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    CreateDeleteTarefaContainer(navHostController = navController)
                }
            }
        }

        composable(Route.Login.route) {
            title.value = "Login"
            LazyColumn(modifier = Modifier.padding(innerPaddings)) {
                item {
                    LoginContainer(navHostController = navController)
                }
            }
        }
    }
}