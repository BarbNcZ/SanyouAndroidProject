package com.androidestudos.fiapchallange.extensions

import android.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.androidestudos.fiapchallange.ui.models.StrokeLabelPieChartRenderer
import com.github.mikephil.charting.charts.PieChart

internal fun PieChart.addStroke(
    labelColor: Int,
    labelSize: TextUnit,
    strokeColor: Int = Color.BLACK,
    strokeWidth: TextUnit = 4f.sp,
) {
    renderer =
        StrokeLabelPieChartRenderer(
            chart = this,
            animator = animator,
            viewPortHandler = viewPortHandler,
            labelColor = labelColor,
            labelSize = labelSize,
            strokeColor = strokeColor,
            strokeWidth = strokeWidth,
        )
}
