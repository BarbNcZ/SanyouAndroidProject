package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetTipoTarefaResult(
    @SerializedName("cd_tipo_tarefa")
    val cdTipoTarefa: Int,
    @SerializedName("ds_tipo_tarefa")
    val dsTipoTarefa: String
)