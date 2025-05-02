package com.androidestudos.fiapchallange.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Calendar


@Serializable
data class GetChartTaskPerRoleResultList (
    @SerializedName("data")
    val data: Array<GetChartTaskPerRoleResult>
)