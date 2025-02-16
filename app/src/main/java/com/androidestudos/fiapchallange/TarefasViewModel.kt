package com.androidestudos.fiapchallange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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