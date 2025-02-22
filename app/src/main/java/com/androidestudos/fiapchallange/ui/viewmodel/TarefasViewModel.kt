package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.repository.TarefasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class TarefasViewModel (
    val repository: TarefasRepository
): ViewModel() {

    private val _state = MutableStateFlow<CreateTarefaResult?>(null)
    val state: StateFlow<CreateTarefaResult?> = _state

    fun createTarefa(dsTarefa: String){
        viewModelScope.launch {
            _state.value = repository.createTarefa(1, dsTarefa).last()
        }

    }

}