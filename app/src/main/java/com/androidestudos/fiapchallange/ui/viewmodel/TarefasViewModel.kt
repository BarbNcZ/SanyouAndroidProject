package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.repository.TasksRepository
import com.androidestudos.fiapchallange.ui.models.TasksEvents
import com.androidestudos.fiapchallange.ui.models.TasksState
import com.androidestudos.fiapchallange.utils.TaskMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TarefasViewModel(
    val repository: TasksRepository
) : ViewModel() {

    private val _state = MutableStateFlow<TasksState>(TasksState())
    val state: StateFlow<TasksState> = _state
    private val _event = Channel<TasksEvents>()
    val event: Flow<TasksEvents> = _event.receiveAsFlow()

    fun getTarefasByFuncionario(cdFuncionario: Int) {
        viewModelScope.launch {
            val mapper: TaskMapper = TaskMapper()
            val tarefa: List<GetTarefaResult> =
                mapper.fromGetTarefaResultListToListOfGetTarefaResult(
                    repository.getTasksByEmployee(cdFuncionario).last()?.tarefas ?: arrayOf()
                )
            _state.value = _state.value.copy(
                tasksByEmployee = tarefa
            )
        }
    }
}