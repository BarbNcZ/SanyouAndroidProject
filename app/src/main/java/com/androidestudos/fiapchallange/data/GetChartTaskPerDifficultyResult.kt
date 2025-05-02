package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Calendar


@Serializable
data class GetChartTaskPerDifficultyResult (
    @SerializedName("nr_dificuldade")
    val nrDificuldade: Int,
    @SerializedName("qtd_tarefas")
    val qtdTarefas: Int
)