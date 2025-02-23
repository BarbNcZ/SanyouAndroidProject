package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class GetTarefasResult (
    @SerializedName("cd_tarefas")
    val cdTarefas: Int,
    @SerializedName("ds_tarefas")
    val dsTarefas: String
)