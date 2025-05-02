package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.repository.ChartsRepository
import com.androidestudos.fiapchallange.repository.RankingRepository
import com.androidestudos.fiapchallange.ui.models.ChartsState
import com.androidestudos.fiapchallange.ui.models.RankingState
import com.androidestudos.fiapchallange.utils.ChartsMapper
import com.androidestudos.fiapchallange.utils.EmployeeMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class ChartsViewModel(
    val rankingRepository: RankingRepository,
    val chartsRepository: ChartsRepository
): ViewModel() {


    private val _state = MutableStateFlow<ChartsState>(ChartsState())
    val state: StateFlow<ChartsState> = _state

    init {

        viewModelScope.launch {

            val mapper: EmployeeMapper = EmployeeMapper()
            val dataPointsPerEmployee: List<GetFuncionarioResult> =
                mapper.fromGetFuncionarioResultListToListOfGetFuncionarioResult(
                    rankingRepository.getRanking().last()?.funcionario ?: arrayOf()
                )

            val chartsMapper: ChartsMapper = ChartsMapper()
            val dataTasksPerRole = chartsMapper.fromGetChartTaskPerRoleResultListToListOfGetChartTaskPerRoleResult(
                chartsRepository.getChartTaskPerRole().last()?.data ?: arrayOf()
            )
            val dataTasksPerDepartment = chartsMapper.fromGetChartTaskPerDepartmentResultListToListOfGetChartTaskPerDepartmentResult(
                chartsRepository.getChartTaskPerDepartment().last()?.data ?: arrayOf()
            )
            val dataTasksPerDifficulty = chartsMapper.fromGetChartTaskPerDifficultyResultListToListOfGetChartTaskPerDifficultyResult(
                chartsRepository.getChartTaskPerDifficulty().last()?.data ?: arrayOf()
            )

            _state.value = _state.value.copy(
                dataPointsPerEmployee = dataPointsPerEmployee.take(5).sortedBy { it.nrPontos },
                dataTasksPerRole = dataTasksPerRole,
                dataTasksPerDepartment = dataTasksPerDepartment,
                dataTasksPerDifficulty = dataTasksPerDifficulty
            )
        }
    }
}