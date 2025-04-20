package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.repository.TasksRepository
import com.androidestudos.fiapchallange.ui.models.TaskEvents
import com.androidestudos.fiapchallange.ui.models.TaskState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TarefaViewModel(
    val repository: TasksRepository
) : ViewModel() {

    private val _state = MutableStateFlow<TaskState>(TaskState())
    val state: StateFlow<TaskState> = _state
    private val _event = Channel<TaskEvents>()
    val event: Flow<TaskEvents> = _event.receiveAsFlow()

    private fun getTarefa(cdTarefa: Int) {
        viewModelScope.launch {
            val tarefa = repository.getTask(cdTarefa)
            _state.value = _state.value.copy(
                tarefa = tarefa.last() ?: GetTarefaResult(-1, "","", 0, 0, 0)
            )
        }
    }
}