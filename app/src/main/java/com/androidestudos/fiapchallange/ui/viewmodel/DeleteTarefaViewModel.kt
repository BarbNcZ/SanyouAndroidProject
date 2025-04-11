package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.repository.EmployeesRepository
import com.androidestudos.fiapchallange.repository.TasksRepository
import com.androidestudos.fiapchallange.ui.models.CreateTaskEvents
import com.androidestudos.fiapchallange.ui.models.CreateTaskState
import com.androidestudos.fiapchallange.ui.models.DeleteTaskEvents
import com.androidestudos.fiapchallange.ui.models.DeleteTaskState
import com.androidestudos.fiapchallange.utils.DeleteTaskMapper
import com.androidestudos.fiapchallange.utils.TaskMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DeleteTarefaViewModel(
    val tasksRepository: TasksRepository,
    val employeesRepository: EmployeesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<DeleteTaskState>(DeleteTaskState())
    val state: StateFlow<DeleteTaskState> = _state
    private val _event = Channel<DeleteTaskEvents>()
    val event: Flow<DeleteTaskEvents> = _event.receiveAsFlow()

    init {
        getTarefas()
    }

    private fun getTarefas() {
        viewModelScope.launch {
            val mapper: TaskMapper = TaskMapper()
            val tarefas: List<GetTarefasResult> =
                mapper.fromGetTarefasResultListToListOfGetTarefasResult(
                    tasksRepository.getTasks().last()?.tarefas ?: arrayOf()
                )
            _state.value = _state.value.copy(
                tasks = tarefas
            )
        }
    }

    fun deleteTask(cdTarefa: Int) {
        viewModelScope.launch {
            val mapper: DeleteTaskMapper = DeleteTaskMapper()
            val result: Boolean? = mapper.fromDeleteTarefaResultToBoolean(
                deleteTarefasResult = tasksRepository.deleteTask(
                    cdTarefa = cdTarefa
                ).last()
            )

            if (result == true) {
                _event.send(DeleteTaskEvents.DeletedSuccessfully)
                getTarefas()
            } else {
                _event.send(DeleteTaskEvents.DeletionFailed)
            }
        }
    }
}