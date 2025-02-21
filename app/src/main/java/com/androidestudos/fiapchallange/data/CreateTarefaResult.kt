package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTarefaResult(
    @SerializedName("id_tarefa")
    val idTarefa: Int


)
