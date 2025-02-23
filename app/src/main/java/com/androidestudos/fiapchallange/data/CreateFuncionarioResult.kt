package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateFuncionarioResult (
    @SerializedName("cd_funcionario")
    val idFuncionario: Int
)