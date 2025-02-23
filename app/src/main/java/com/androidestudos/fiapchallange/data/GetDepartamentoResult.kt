package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetDepartamentoResult (
    @SerializedName("cd_depto")
    val cdDepartamento: Int,
    @SerializedName("nm_depto")
    val nmDepartamento: String

)