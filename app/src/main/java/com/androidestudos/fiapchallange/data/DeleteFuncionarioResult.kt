package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteFuncionarioResult (
    @SerializedName("result")
    val result: Boolean
)
