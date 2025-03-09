package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetTarefaResultList (
    @SerializedName("tarefas")
    val tarefas: Array<GetTarefaResult>
)