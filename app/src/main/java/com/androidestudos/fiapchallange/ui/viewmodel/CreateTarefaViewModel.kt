package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult
import com.androidestudos.fiapchallange.repository.EmployeesRepository
import com.androidestudos.fiapchallange.repository.TasksRepository
import com.androidestudos.fiapchallange.ui.models.CreateTaskEvents
import com.androidestudos.fiapchallange.ui.models.CreateTaskState
import com.androidestudos.fiapchallange.utils.EmployeeMapper
import com.androidestudos.fiapchallange.utils.TaskTypeMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateTarefaViewModel(
    val tasksRepository: TasksRepository,
    val employeesRepository: EmployeesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<CreateTaskState>(CreateTaskState())
    val state: StateFlow<CreateTaskState> = _state
    private val _event = Channel<CreateTaskEvents>()
    val event: Flow<CreateTaskEvents> = _event.receiveAsFlow()

    init {
        getTipoTarefa()
        getFuncionario()
    }

    fun createTask(cdTipoTarefa: Int, cdFuncionario: Int, dsTarefa: String, estimation: Int, time: Long) {
        viewModelScope.launch {
            val taskId = tasksRepository.createTask(
                taskTypeId = cdTipoTarefa,
                employeeId = cdFuncionario,
                task = dsTarefa,
                estimation = estimation,
                time = time
            ).last()?.idTarefa ?: -1
            if (taskId > -1) {
                _event.send(CreateTaskEvents.CreatedSuccessfully)
            } else {
                _event.send(CreateTaskEvents.CreationFailed)
            }
        }
    }

    private fun getTipoTarefa() {
        viewModelScope.launch {
            val mapper: TaskTypeMapper = TaskTypeMapper()
            val tiposTarefa: List<GetTipoTarefaResult> =
                mapper.fromGetTipoTarefaResultListToListOfGetTipoTarefaResult(
                    tasksRepository.getTaskType().last()?.tipoTarefas ?: arrayOf()
                )
            _state.value = _state.value.copy(
                taskTypes = tiposTarefa
            )
        }
    }

    private fun getFuncionario() {
        viewModelScope.launch {
            val mapper: EmployeeMapper = EmployeeMapper()
            val funcionario: List<GetFuncionarioResult> =
                mapper.fromGetFuncionarioResultListToListOfGetFuncionarioResult(
                    employeesRepository.getEmployees().last()?.funcionario ?: arrayOf()
                )
            _state.value = _state.value.copy(
                employees = funcionario
            )
        }
    }
}