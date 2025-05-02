package com.androidestudos.fiapchallange.ui.view.atoms.charts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.androidestudos.fiapchallange.ui.models.BarsChartData
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData as HorizontalBarData
import com.github.mikephil.charting.data.BarDataSet as HorizontalBarDataSet
import com.github.mikephil.charting.data.BarEntry as HorizontalBarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlin.collections.indexOf
import kotlin.collections.map
import android.graphics.Color as NotComposeColor

@Composable
fun HorizontalBarsChartView(
    data: List<BarsChartData>,
    label: String,
    axisLabel: Boolean = false,
    usePercentageBasedAmongData: Boolean = false,
    legendHorizontalAlignment: LegendHorizontalAlignment = LegendHorizontalAlignment.CENTER,
    forceLegend: Boolean = false,
    labelSize: TextUnit = 14f.sp,
    legendForm: Legend.LegendForm = Legend.LegendForm.DEFAULT,
    labelColor: Int = NotComposeColor.BLACK,
    barsColor: Int = NotComposeColor.BLUE,
    modifier: Modifier = Modifier
) {
    val usePercentage = data.all { it.value2 != null }
    Box(modifier = modifier) {
        AndroidView(
            factory = { context ->
                HorizontalBarChart(context).apply {
                    description.isEnabled = false
                    axisRight.isEnabled = false
                    setFitBars(true)

                    axisLeft.setDrawGridLines(false)

                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.setDrawGridLines(false)
                    xAxis.granularity = 1f

                    legend.horizontalAlignment = legendHorizontalAlignment
                    legend.isEnabled = if (forceLegend) true else !axisLabel && label.isNotEmpty()
                    legend.form = legendForm
                }
            },
            update = { chart ->
                val entries = data.mapIndexed { index, entry ->
                    HorizontalBarEntry(
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
                        entry.value
                    )
                }

                val dataSet = HorizontalBarDataSet(entries, label).apply {
                    color = barsColor
                    valueTextColor = labelColor
                    valueTextSize = labelSize.value
                    valueFormatter = object : ValueFormatter() {
                        override fun getBarLabel(barEntry: HorizontalBarEntry?): String? {
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
                    axisMinimum = 0f                     // Start from 0
                    isGranularityEnabled = true          // Enforce granularity
                    if (usePercentage || usePercentageBasedAmongData) {
                        axisMaximum = 100f
                        granularity = 10f
                        setLabelCount(11, true)
                    }
                }

                chart.data = HorizontalBarData(dataSet).apply {
                    barWidth = 0.8f
                }

                chart.invalidate()
            },
            modifier = Modifier
                .matchParentSize()
                .padding(top = if (axisLabel && label.isNotEmpty()) { 32.dp } else 0.dp) // Leave space for axis labels
        )

        if (axisLabel && label.isNotEmpty()) {
            Text(
                text = label,
                modifier = Modifier
                    .align(Alignment.TopCenter),
                fontSize = labelSize,
            )
        }
    }
}