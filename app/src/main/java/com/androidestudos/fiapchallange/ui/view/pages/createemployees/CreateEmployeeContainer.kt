package com.androidestudos.fiapchallange.ui.view.pages.createemployees

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidestudos.fiapchallange.ui.viewmodel.EmployeesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable

fun CreateEmployeeContainer (
    viewModel: EmployeesViewModel = koinViewModel()
){

    val state = viewModel.state.collectAsStateWithLifecycle()

    CreateEmployeeScreen(
        viewModel::createEmployee,
        state.value.employeeId,
        state.value.departments,
        state.value.roles
    )
}