package com.androidestudos.fiapchallange.ui.models

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Align
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.renderer.PieChartRenderer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler
import android.graphics.Color as NotComposeColor

class StrokeLabelPieChartRenderer(
    chart: PieChart,
    animator: com.github.mikephil.charting.animation.ChartAnimator,
    viewPortHandler: ViewPortHandler,
    private val labelColor: Int,
    private val labelSize: TextUnit,
    private val strokeColor: Int = NotComposeColor.BLACK,
    private val strokeWidth: TextUnit = 4f.sp,
) : PieChartRenderer(chart, animator, viewPortHandler) {

    val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this@apply.color = labelColor
        this@apply.textAlign = Align.CENTER
        this@apply.textSize = Utils.convertDpToPixel(labelSize.value)
    }

    private val strokePaint = Paint(fillPaint).apply {
        this@apply.style = Paint.Style.STROKE
        this@apply.strokeWidth = this@StrokeLabelPieChartRenderer.strokeWidth.value
        this@apply.color = strokeColor
    }

    override fun drawEntryLabel(c: Canvas, label: String?, x: Float, y: Float) {
        label ?: return

        // Drawing the stroke
        c.drawText(label, x, y, strokePaint)

        // Drawing the fill (foreground)
        c.drawText(label, x, y, fillPaint)
    }

    override fun drawValue(c: Canvas, valueText: String?, x: Float, y: Float, color: Int) {
        valueText ?: return

        c.drawText(valueText, x, y, strokePaint)
        c.drawText(valueText, x, y, fillPaint)
    }
}
