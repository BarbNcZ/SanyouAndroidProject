package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetCargoResultList (
    @SerializedName("cargo")
    val cargo: Array<GetCargoResult>
)