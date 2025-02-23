package com.androidestudos.fiapchallange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult
import com.androidestudos.fiapchallange.repository.TarefasRepository
import com.androidestudos.fiapchallange.ui.models.TarefasEvents
import com.androidestudos.fiapchallange.ui.models.TarefasState
import com.androidestudos.fiapchallange.utils.CargoMapper
import com.androidestudos.fiapchallange.utils.DelTarefaMapper
import com.androidestudos.fiapchallange.utils.DepartamentoMapper
import com.androidestudos.fiapchallange.utils.TarefaMapper
import com.androidestudos.fiapchallange.utils.TipoTarefaMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TarefasViewModel (
    val repository: TarefasRepository
): ViewModel() {

    private val _state = MutableStateFlow<TarefasState>(TarefasState())
    val state: StateFlow<TarefasState> = _state
    private val _event = Channel<TarefasEvents>()
    val event: Flow<TarefasEvents> = _event.receiveAsFlow()

    init{
        getTipoTarefa()
        getTarefas()
        getCargo()
        getDepartamento()
    }

    fun createTarefa(cdTipoTarefa: Int, dsTarefa: String){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                idTarefa = repository.createTarefa(
                    cdTipoTarefa = cdTipoTarefa,
                    dsTarefas = dsTarefa
                ).last()?.idTarefa ?: -1
            )

        }

    }

    private fun getTipoTarefa(){
        viewModelScope.launch {
            val mapper: TipoTarefaMapper = TipoTarefaMapper()
            val tiposTarefa: List<GetTipoTarefaResult> =
                mapper.fromGetTipoTarefaResultListToListOfGetTipoTarefaResult(
                    repository.getTipoTarefa().last()?.tipoTarefas ?: arrayOf()
                )
            _state.value = _state.value.copy(
                tiposTarefa =  tiposTarefa
            )

        }

    }

    private fun getTarefas(){
        viewModelScope.launch {
            val mapper: TarefaMapper = TarefaMapper()
            val tarefas: List<GetTarefasResult> =
                mapper.fromGetTarefasResultListToListOfGetTarefasResult(
                    repository.getTarefas().last()?.tarefas ?: arrayOf()
                )
            _state.value = _state.value.copy(
                tarefas =  tarefas
            )

        }

    }

    fun deleteTarefa(cdTarefa: Int){
        viewModelScope.launch {
            val mapper: DelTarefaMapper = DelTarefaMapper()
            val result: Boolean? = mapper.fromDeleteTarefaResultToBoolean(
                    deleteTarefasResult = repository.deleteTarefa(
                            cdTarefa = cdTarefa
                            ).last()
                    )

            if (result == true){
                _event.send(TarefasEvents.DeletedSuccessfully)
                getTarefas()
            }

            else{
                _event.send(TarefasEvents.DeletionFailed)
            }

        }

    }

    fun createFuncionario(
        cdDepto: Int,
        cdCargo: Int,
        dsEmail: String,
        nmFuncionario: String
    ){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                idFuncionario = repository.createFuncionario(
                    cdDepto,
                    cdCargo,
                    dsEmail,
                    nmFuncionario
                ).last()?.idFuncionario ?: -1
            )

        }

    }

    private fun getCargo(){
        viewModelScope.launch {
            val mapper: CargoMapper = CargoMapper()
            val cargo: List<GetCargoResult> =
                mapper.fromGetCargoResultListToListOfGetCargoResult(
                    repository.getCargo().last()?.cargo ?: arrayOf()
                )
            _state.value = _state.value.copy(
                cargos =  cargo
            )

        }

    }

    private fun getDepartamento(){
        viewModelScope.launch {
            val mapper: DepartamentoMapper = DepartamentoMapper()
            val departamento: List<GetDepartamentoResult> =
                mapper.fromGetDepartamentoResultListToListOfGetDepartamentoResult(
                    repository.getDepartamento().last()?.depto ?: arrayOf()
                )
            _state.value = _state.value.copy(
                departamentos =  departamento
            )

        }

    }

}