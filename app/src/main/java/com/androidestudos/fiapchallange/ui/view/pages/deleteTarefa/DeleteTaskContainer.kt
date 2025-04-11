package com.androidestudos.fiapchallange.ui.view.pages.deleteTarefa

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
import com.androidestudos.fiapchallange.ui.models.DeleteTaskEvents
import com.androidestudos.fiapchallange.ui.viewmodel.DeleteTarefaViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeleteTaskContainer(
    viewModel: DeleteTarefaViewModel = koinViewModel(),
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
                            DeleteTaskEvents.DeletedSuccessfully ->
                                Toast.makeText(context, "Tarefa deletada", Toast.LENGTH_SHORT).show()
                            DeleteTaskEvents.DeletionFailed ->
                                Toast.makeText(context, "Erro ao deletar tarefa", Toast.LENGTH_SHORT).show()
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

    DeleteTaskScreen(
        tasks = state.value.tasks,
        deleteTask = viewModel::deleteTask
    )
}