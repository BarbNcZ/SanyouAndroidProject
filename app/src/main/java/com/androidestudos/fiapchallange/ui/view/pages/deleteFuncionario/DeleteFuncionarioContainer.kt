package com.androidestudos.fiapchallange.ui.view.pages.deleteFuncionario

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
import com.androidestudos.fiapchallange.ui.models.DeleteEmployeeEvents
import com.androidestudos.fiapchallange.ui.models.DeleteTaskEvents
import com.androidestudos.fiapchallange.ui.viewmodel.DeleteFuncionarioViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.DeleteTarefaViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeleteFuncionarioContainer(
    viewModel: DeleteFuncionarioViewModel = koinViewModel(),
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
                            DeleteEmployeeEvents.DeletedSuccessfully ->
                                Toast.makeText(context, "Funcionario deletado", Toast.LENGTH_SHORT).show()
                            DeleteEmployeeEvents.DeletionFailed ->
                                Toast.makeText(context, "Erro ao deletar funcionario", Toast.LENGTH_SHORT).show()
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

    DeleteFuncionarioScreen(
        funcionarios = state.value.funcionarios,
        deleteFuncionario = viewModel::deleteFuncionario
    )
}