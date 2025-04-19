package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.repository.EmployeesRepository
import com.androidestudos.fiapchallange.ui.models.CreateEmployeesEvents
import com.androidestudos.fiapchallange.ui.models.DeleteEmployeeEvents
import com.androidestudos.fiapchallange.ui.models.DeleteEmployeeState
import com.androidestudos.fiapchallange.utils.DeleteEmployeeMapper
import com.androidestudos.fiapchallange.utils.EmployeeMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DeleteFuncionarioViewModel(
    val employeesRepository: EmployeesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DeleteEmployeeState>(DeleteEmployeeState())
    val state: StateFlow<DeleteEmployeeState> = _state
    private val _event = Channel<DeleteEmployeeEvents>()
    val event: Flow<DeleteEmployeeEvents> = _event.receiveAsFlow()

    init {
        getFuncionario()
    }

    private fun getFuncionario() {
        viewModelScope.launch {
            val mapper: EmployeeMapper = EmployeeMapper()
            val funcionarios: List<GetFuncionarioResult> =
                mapper.fromGetFuncionarioResultListToListOfGetFuncionarioResult(
                    employeesRepository.getEmployees().last()?.funcionario ?: arrayOf()
                )
            _state.value = _state.value.copy(
                funcionarios = funcionarios
            )
        }
    }

    fun deleteFuncionario(cdFuncionario: Int) {
        viewModelScope.launch {
            val mapper: DeleteEmployeeMapper = DeleteEmployeeMapper()
            val result: Boolean? = mapper.fromDeleteFuncionarioResultToBoolean(
                deleteFuncionarioResult = employeesRepository.deleteFuncionario(
                    cdFuncionario = cdFuncionario
                ).last()
            )

            if (result == true) {
                _event.send(DeleteEmployeeEvents.DeletedSuccessfully)
                getFuncionario()
            } else {
                _event.send(DeleteEmployeeEvents.DeletionFailed)
            }
        }
    }
}