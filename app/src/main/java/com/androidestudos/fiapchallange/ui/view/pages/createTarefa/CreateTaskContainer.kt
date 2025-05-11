package com.androidestudos.fiapchallange.ui.view.pages.createTarefa

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
import com.androidestudos.fiapchallange.ui.models.CreateTaskEvents
import com.androidestudos.fiapchallange.ui.viewmodel.CreateTarefaViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateTaskContainer(
    viewModel: CreateTarefaViewModel = koinViewModel(),
    navHostController: NavHostController
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
                            CreateTaskEvents.CreatedSuccessfully ->
                                Toast.makeText(context, "Tarefa criada", Toast.LENGTH_SHORT).show()
                            CreateTaskEvents.CreationFailed ->
                                Toast.makeText(context, "Erro ao criar tarefa", Toast.LENGTH_SHORT).show()
                            else -> Unit
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

    CreateTaskScreen(
        taskTypes = state.value.taskTypes,
        createTask = viewModel::createTask,
        employees = state.value.employees
    )
}