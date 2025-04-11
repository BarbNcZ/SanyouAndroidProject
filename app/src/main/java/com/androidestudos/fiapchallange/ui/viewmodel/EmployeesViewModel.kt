package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult
import com.androidestudos.fiapchallange.repository.DepartmentsRepository
import com.androidestudos.fiapchallange.repository.EmployeesRepository
import com.androidestudos.fiapchallange.repository.RolesRepository
import com.androidestudos.fiapchallange.repository.TasksRepository
import com.androidestudos.fiapchallange.ui.models.EmployeesEvents
import com.androidestudos.fiapchallange.ui.models.EmployeesState
import com.androidestudos.fiapchallange.utils.RoleMapper
import com.androidestudos.fiapchallange.utils.DepartmentMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class EmployeesViewModel(
    val employeesRepository: EmployeesRepository,
    val rolesRepository: RolesRepository,
    val departmentsRepository: DepartmentsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<EmployeesState>(EmployeesState())
    val state: StateFlow<EmployeesState> = _state
    private val _event = Channel<EmployeesEvents>()
    val event: Flow<EmployeesEvents> = _event.receiveAsFlow()

    init {
        getCargo()
        getDepartamento()
    }

    fun createEmployee(
        cdDepto: Int,
        cdCargo: Int,
        dsEmail: String,
        nmFuncionario: String
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                employeeId = employeesRepository.createEmployee(
                    cdDepto,
                    cdCargo,
                    dsEmail,
                    nmFuncionario
                ).last()?.idFuncionario ?: -1
            )
        }
    }

    private fun getCargo() {
        viewModelScope.launch {
            val mapper: RoleMapper = RoleMapper()
            val cargo: List<GetCargoResult> =
                mapper.fromGetCargoResultListToListOfGetCargoResult(
                    rolesRepository.getRole().last()?.cargo ?: arrayOf()
                )
            _state.value = _state.value.copy(
                roles = cargo
            )
        }
    }

    private fun getDepartamento() {
        viewModelScope.launch {
            val mapper: DepartmentMapper = DepartmentMapper()
            val departamento: List<GetDepartamentoResult> =
                mapper.fromGetDepartamentoResultListToListOfGetDepartamentoResult(
                    departmentsRepository.getDepartment().last()?.depto ?: arrayOf()
                )
            _state.value = _state.value.copy(
                departments = departamento
            )
        }
    }
}