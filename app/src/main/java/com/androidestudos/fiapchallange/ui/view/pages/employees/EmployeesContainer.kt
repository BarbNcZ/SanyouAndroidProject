package com.androidestudos.fiapchallange.ui.view.pages.employees

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidestudos.fiapchallange.ui.models.TarefasEvents
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmployeesContainer(
    viewModel: TarefasViewModel = koinViewModel()
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

    EmployeesScreen(
        state.value.idFuncionario,
        viewModel::createFuncionario,
        state.value.departamentos,
        state.value.cargos
    )
}