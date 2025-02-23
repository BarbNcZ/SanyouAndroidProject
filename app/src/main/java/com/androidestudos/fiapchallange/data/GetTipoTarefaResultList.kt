package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class GetTipoTarefaResultList(
    @SerializedName("tipo_tarefa")
    val tipoTarefas: Array<GetTipoTarefaResult>
)
