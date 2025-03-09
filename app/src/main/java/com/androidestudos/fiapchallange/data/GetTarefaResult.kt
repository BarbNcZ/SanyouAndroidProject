package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class GetTarefaResult (
    @SerializedName("ds_tarefas")
    val dsTarefas: String,
    @SerializedName("ds_tipo_tarefa")
    val dsTipoTarefa: String
)