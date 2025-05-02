package com.androidestudos.fiapchallange.ui.models

import android.graphics.Color

data class  DonutOrPizzaChartData(
    val label: String = "",
    val value: Float = 0f,
    val value2: Float? = null,
    val color: Int = Color.GRAY,
)