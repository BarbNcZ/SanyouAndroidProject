package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Calendar


@Serializable
data class GetChartTaskPerRoleResult (
    @SerializedName("ds_cargo")
    val dsCargo: String,
    @SerializedName("qtd_tarefas")
    val qtdTarefas: Int,
    @SerializedName("qtd_tarefasTotal")
    val qtdTarefasTotal: Int
)