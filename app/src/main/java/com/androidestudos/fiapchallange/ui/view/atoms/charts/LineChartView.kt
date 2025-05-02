package com.androidestudos.fiapchallange.ui.view.atoms.charts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import android.graphics.Color as NotComposeColor

@Composable
fun LineChartView(
    dataPoints: Map<String, Float>,
    xAxisLabel: String,
    yAxisLabel: String,
    axisLabel: Boolean = false,
    axisOffset: Dp = (-10f).dp,
    drawValuesInPoints: Boolean = true,
    forceLegend: Boolean = false,
    legendForm: Legend.LegendForm = Legend.LegendForm.DEFAULT,
    legendHorizontalAlignment: LegendHorizontalAlignment = LegendHorizontalAlignment.CENTER,
    lineWidth: Float = 2f,
    circleRadius: Float = 4f,
    labelSize: TextUnit = 14f.sp,
    labelColor: Int = NotComposeColor.BLACK,
    circleColor: Int = NotComposeColor.CYAN,
    lineColor: Int = NotComposeColor.BLUE,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    start = if (axisLabel) { 32.dp } else 0.dp,
                    bottom = if (axisLabel) { 32.dp } else 0.dp,
                ),
            factory = { context ->
                LineChart(context).apply {
                    setTouchEnabled(true)
                    setPinchZoom(true)
                    description.isEnabled = false
                    axisRight.isEnabled = false

                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.setDrawGridLines(false)
                    axisLeft.setDrawGridLines(true)

                    legend.horizontalAlignment = legendHorizontalAlignment
                    legend.isEnabled = if (forceLegend) true else !axisLabel && yAxisLabel.isNotEmpty()
                    legend.form = legendForm
                }
            },
            update = { chart ->
                val entries = dataPoints.entries.mapIndexed { index, value ->
                    Entry(index.toFloat(), value.value)
                }

                val dataSet = LineDataSet(entries, yAxisLabel).apply {
                    color = lineColor
                    valueTextColor = labelColor
                    this@apply.lineWidth = lineWidth
                    setDrawCircles(true)
                    this@apply.setCircleColor(circleColor)
                    this@apply.circleRadius = circleRadius
                    valueTextSize = 14f
                    setDrawValues(drawValuesInPoints)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return value.toInt().toString()
                        }
                    }
                }

                val xLabels = dataPoints.keys
                chart.xAxis.apply {
                    valueFormatter = IndexAxisValueFormatter(xLabels)
                    granularity = 1f
                    axisMinimum = 1f
                    isGranularityEnabled = true
                    setLabelCount(5, true)
                    setDrawGridLines(true)
                    position = XAxis.XAxisPosition.BOTTOM
                }
                chart.axisLeft.apply {
                    axisMinimum = 0f
                    granularity = 1f
                    isGranularityEnabled = true
                    setDrawGridLines(true)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return value.toInt().toString()
                        }
                    }
                }
                chart.axisRight.isEnabled = false

                chart.data = LineData(dataSet)
                chart.invalidate()
            }
        )

        if (axisLabel) {
            if (yAxisLabel.isNotEmpty()) {
                Text(
                    text = yAxisLabel,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(axisOffset)
                        .graphicsLayer {
                            rotationZ = -90f
                        },
                    fontSize = labelSize,
                )
            }
            if (xAxisLabel.isNotEmpty()) {
                Text(
                    text = xAxisLabel,
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    fontSize = labelSize,
                )
            }
        }
    }
}