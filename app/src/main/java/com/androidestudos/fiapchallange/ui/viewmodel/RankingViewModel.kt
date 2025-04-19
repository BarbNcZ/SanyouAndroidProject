package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.repository.RankingRepository
import com.androidestudos.fiapchallange.ui.models.LoginState
import com.androidestudos.fiapchallange.ui.models.RankingState
import com.androidestudos.fiapchallange.utils.EmployeeMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class RankingViewModel(
    val repository: RankingRepository
): ViewModel() {



    private val _state = MutableStateFlow<RankingState>(RankingState())
    val state: StateFlow<RankingState> = _state

    init {

        viewModelScope.launch {

            val mapper: EmployeeMapper = EmployeeMapper()
            val funcionario: List<GetFuncionarioResult> =
                mapper.fromGetFuncionarioResultListToListOfGetFuncionarioResult(
                    repository.getRanking().last()?.funcionario ?: arrayOf()
                )

            _state.value = _state.value.copy(
                funcionarios = funcionario
            )
        }
    }
}