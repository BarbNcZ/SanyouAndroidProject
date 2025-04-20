package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class GetFuncionarioResult (
    @SerializedName("cd_funcionario")
    val cdFuncionario: Int,
    @SerializedName("nm_depto")
    val nmDepto: String,
    @SerializedName("ds_cargo")
    val dsCargo: String,
    @SerializedName("nm_funcionario")
    val nmFuncionario: String,
    @SerializedName("nr_pontos")
    val nrPontos: Int
)