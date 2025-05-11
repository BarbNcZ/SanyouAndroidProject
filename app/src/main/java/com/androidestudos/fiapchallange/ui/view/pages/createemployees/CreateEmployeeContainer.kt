package com.androidestudos.fiapchallange.ui.view.pages.createemployees

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidestudos.fiapchallange.ui.models.CreateEmployeesEvents
import com.androidestudos.fiapchallange.ui.viewmodel.EmployeesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable

fun CreateEmployeeContainer (
    viewModel: EmployeesViewModel = koinViewModel()
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
                            CreateEmployeesEvents.CreateddSuccessfully ->
                                Toast.makeText(context, "Funcionário criado", Toast.LENGTH_SHORT).show()
                            CreateEmployeesEvents.CreationFailed ->
                                Toast.makeText(context, "Erro ao criar funcionário", Toast.LENGTH_SHORT).show()
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

    CreateEmployeeScreen(
        viewModel::createEmployee,
        state.value.departments,
        state.value.roles
    )
}