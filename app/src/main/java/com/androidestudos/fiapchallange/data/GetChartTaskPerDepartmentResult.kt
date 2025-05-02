package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class GetChartTaskPerDepartmentResult (
    @SerializedName("nm_depto")
    val nmDepto: String,
    @SerializedName("qtd_tarefas")
    val qtdTarefas: Int,
    @SerializedName("qtd_tarefasTotal")
    val qtdTarefasTotal: Int
)