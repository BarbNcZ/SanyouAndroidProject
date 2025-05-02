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
import androidx.core.graphics.toColorInt
import com.androidestudos.fiapchallange.ui.models.BarsChartData
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlin.collections.indexOf
import android.graphics.Color as NotComposeColor

@Composable
fun VerticalBarsChartView(
    data: List<BarsChartData>,
    label: String,
    axisLabel: Boolean = false,
    axisOffset: Dp = (-10f).dp,
    usePercentageBasedAmongData: Boolean = false,
    forceLegend: Boolean = false,
    legendHorizontalAlignment: LegendHorizontalAlignment = LegendHorizontalAlignment.CENTER,
    labelSize: TextUnit = 14f.sp,
    legendForm: Legend.LegendForm = Legend.LegendForm.DEFAULT,
    labelColor: Int = NotComposeColor.BLACK,
    barsColor: Int = "#800080".toColorInt(),
    modifier: Modifier = Modifier
) {
    val usePercentage = data.all { it.value2 != null }
    Box(modifier = modifier) {
        AndroidView(
            factory = { context ->
                BarChart(context).apply {
                    description.isEnabled = false
                    axisRight.isEnabled = false
                    setFitBars(true)

                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.granularity = 1f
                    xAxis.setDrawGridLines(false)
                    axisLeft.setDrawGridLines(true)
                    legend.horizontalAlignment = legendHorizontalAlignment
                    legend.isEnabled = if (forceLegend) true else !axisLabel && label.isNotEmpty()
                    legend.form = legendForm
                }
            },
            update = { chart ->
                val entries = data.mapIndexed { index, entry ->
                    BarEntry(
                        index.toFloat(),
                        if (usePercentage) {
                            val divisor = if (usePercentageBasedAmongData) {
                                data.map { (it.value / it.value2!!) }.sum()
                            } else 1f
                            ((entry.value / entry.value2!!) / divisor) * 100f
                        } else if (usePercentageBasedAmongData) {
                            (entry.value / data.map { it.value }.sum()) * 100f
                        } else {
                            entry.value
                        },
                    )
                }

                val dataSet = BarDataSet(entries, label).apply {
                    color = barsColor
                    valueTextColor = labelColor
                    valueTextSize = labelSize.value
                    valueFormatter = object : ValueFormatter() {
                        override fun getBarLabel(barEntry: BarEntry?): String? {
                            val index = entries.indexOf(barEntry)
                            val entry = data.getOrNull(index)
                            return if(entry != null) {
                                if (usePercentage) {
                                    "${entry.value.toInt()}/${entry.value2?.toInt().toString()}"
                                } else {
                                    entry.value.toInt().toString()
                                }
                            } else {
                                super.getBarLabel(barEntry)
                            }
                        }
                    }
                }

                chart.xAxis.valueFormatter = IndexAxisValueFormatter(data.map { it.label }.toList())
                chart.axisLeft.apply {
                    axisMinimum = 0f
                    isGranularityEnabled = true
                    if (usePercentage || usePercentageBasedAmongData) {
                        axisMaximum = 100f
                        granularity = 10f
                        setLabelCount(11, true)
                    }
                }

                chart.data = BarData(dataSet)
                chart.invalidate()
            },
            modifier = Modifier
                .matchParentSize()
                .padding(start = if (axisLabel && label.isNotEmpty()) { 32.dp } else 0.dp) // Leave space for axis labels
        )

        if (axisLabel && label.isNotEmpty()) {
            Text(
                text = label,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(axisOffset)
                    .graphicsLayer {
                        rotationZ = -90f
                    },
                fontSize = labelSize,
            )
        }
    }
}