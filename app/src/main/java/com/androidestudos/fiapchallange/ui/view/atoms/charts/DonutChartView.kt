package com.androidestudos.fiapchallange.ui.view.atoms.charts

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.androidestudos.fiapchallange.extensions.addStroke
import com.androidestudos.fiapchallange.ui.models.DonutOrPizzaChartData
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment
import com.github.mikephil.charting.formatter.ValueFormatter
import android.graphics.Color as NotComposeColor
import com.github.mikephil.charting.charts.PieChart as DonutChart
import com.github.mikephil.charting.data.PieData as DonutData
import com.github.mikephil.charting.data.PieDataSet as DonutDataSet
import com.github.mikephil.charting.data.PieEntry as DonutEntry

@Composable
fun DonutChartView(
    data: List<DonutOrPizzaChartData>,
    addStroke: Boolean = true,
    showComparisonWithDataSum: Boolean = false,
    legendHorizontalAlignment: LegendHorizontalAlignment = LegendHorizontalAlignment.CENTER,
    labelColor: Int = Color.WHITE,
    labelSize: TextUnit = 14f.sp,
    strokeColor: Int = Color.BLACK,
    strokeWidth: TextUnit = 4f.sp,
    modifier: Modifier = Modifier,
) {
    val usePercentage = data.all { it.value2 != null }
    AndroidView(
        factory = { context ->
            DonutChart(context).apply {
                setUsePercentValues(true)
                description.isEnabled = false
                isDrawHoleEnabled = true
                setHoleColor(NotComposeColor.TRANSPARENT)
                setEntryLabelColor(labelColor)
                setEntryLabelTextSize(labelSize.value)
                legend.horizontalAlignment = legendHorizontalAlignment
                legend.isEnabled = true

                if (addStroke) {
                    addStroke(
                        labelColor = labelColor,
                        labelSize = labelSize,
                        strokeColor = strokeColor,
                        strokeWidth = strokeWidth,
                    )
                }
            }
        },
        update = { donutChart ->
            val entries = data.map {
                DonutEntry(
                    if (usePercentage) {
                        (it.value / it.value2!!) / data.map { (it.value / it.value2!!) }.sum()
                    } else {
                        it.value / data.map { it.value }.sum()
                    },
                    it.label
                )
            }
            val dataSet = DonutDataSet(entries, "").apply {
                colors = data.map { it.color }
                sliceSpace = 3f
                selectionShift = 5f
                valueFormatter = object : ValueFormatter() {
                    override fun getPieLabel(value: Float, pieEntry: DonutEntry?): String? {
                        val index = entries.indexOf(pieEntry)
                        val entry = data.getOrNull(index)
                        return if(entry != null) {
                            if (showComparisonWithDataSum && usePercentage) {
                                "${entry.value.toInt()}/${entry.value2?.toInt().toString()}"
                            } else if (showComparisonWithDataSum) {
                                "${entry.value.toInt()}/${data.map { it.value }.sum().toInt()}"
                            } else {
                                entry.value.toInt().toString()
                            }
                        } else {
                            super.getPieLabel(value, pieEntry)
                        }
                    }
                }
            }

            val donutData = DonutData(dataSet).apply {
                setValueTextSize(labelSize.value)
                setValueTextColor(labelColor)
            }

            donutChart.data = donutData
            // donutChart.animateY(500) // uncomment to implement rotation animation when rendering
            donutChart.invalidate() // refresh chart
        },
        modifier = modifier
    )
}