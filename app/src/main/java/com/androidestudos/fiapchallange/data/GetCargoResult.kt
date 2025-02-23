package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetCargoResult (
    @SerializedName("cd_cargo")
    val cdCargo: Int,
    @SerializedName("ds_cargo")
    val dsCargo: String

)
