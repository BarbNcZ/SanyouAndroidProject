package com.androidestudos.fiapchallange.ui.view.pages.tarefas

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.Route
import com.androidestudos.fiapchallange.ui.models.TarefasEvents
import com.androidestudos.fiapchallange.ui.view.pages.employees.EmployeesScreen
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun TarefasContainer(
    viewModel: TarefasViewModel = koinViewModel(),
    navHostController: NavHostController,
){

    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val lifeCycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifeCycleOwner) {

        val observer = LifecycleEventObserver{ _, event ->
            if( event == Lifecycle.Event.ON_CREATE ){

                scope.launch {
                    viewModel.event.collect{ event ->
                        when(event){
                            TarefasEvents.DeletedSuccessfully ->
                                Toast.makeText(context, "Tarefa deletada", Toast.LENGTH_SHORT).show()
                            TarefasEvents.DeletionFailed ->
                                Toast.makeText(context, "Erro ao deletar tarefa", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }

    TarefasScreen(
        goToEmployeesScreen = {
            navHostController.navigate(Route.Employees.route)
        },
        state.value.idTarefa,
        state.value.tiposTarefa,
        state.value.tarefas,
        viewModel::createTarefa,
        viewModel::deleteTarefa,
        state.value.funcionarios
    )
}