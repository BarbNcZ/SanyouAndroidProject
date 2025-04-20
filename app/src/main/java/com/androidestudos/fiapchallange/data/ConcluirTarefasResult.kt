package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConcluirTarefasResult (
    @SerializedName("result")
    val result: Boolean
)
