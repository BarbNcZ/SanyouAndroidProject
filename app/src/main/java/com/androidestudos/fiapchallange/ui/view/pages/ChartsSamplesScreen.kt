package com.androidestudos.fiapchallange.ui.view.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.androidestudos.fiapchallange.ui.models.BarsChartData
import com.androidestudos.fiapchallange.ui.models.DonutOrPizzaChartData
import android.graphics.Color as NotComposeColor
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme
import com.androidestudos.fiapchallange.ui.view.atoms.charts.DonutChartView
import com.androidestudos.fiapchallange.ui.view.atoms.charts.HorizontalBarsChartView
import com.androidestudos.fiapchallange.ui.view.atoms.charts.LineChartView
import com.androidestudos.fiapchallange.ui.view.atoms.charts.PizzaChartView
import com.androidestudos.fiapchallange.ui.view.atoms.charts.VerticalBarsChartView

@Composable
fun ChartsSamplesScreen (){
    LazyColumn (Modifier.height(1080.dp)
        .fillMaxWidth()

    ) {
        item {
            val data: List<BarsChartData> = listOf(
                BarsChartData(
                    label = "John",
                    value = 85f,
                ),
                BarsChartData(
                    label = "Jessica",
                    value = 90f,
                ),
                BarsChartData(
                    label = "Alfred",
                    value = 75f,
                ),
                BarsChartData(
                    label = "Ashley",
                    value = 60f,
                ),
                BarsChartData(
                    label = "Bob",
                    value = 35f,
                ),
            )

            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Top 5 Points per Employee",
                        fontSize = 14f.sp
                    )
                }
                HorizontalBarsChartView(
                    data = data,
                    label = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp)
                )
                HorizontalDivider()
            }
        }

        item {
            val columnData: List<BarsChartData> = listOf(
                BarsChartData(
                    label = "Cashier",
                    value = 4f,
                    value2 = 6f
                ),
                BarsChartData(
                    label = "Manager",
                    value = 3f,
                    value2 = 7f
                ),
                BarsChartData(
                    label = "Stocker",
                    value = 5f,
                    value2 = 10f
                ),
            )

            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Tasks Done per Role",
                        fontSize = 14f.sp
                    )
                }
                VerticalBarsChartView(
                    data = columnData,
                    label = "% of all tasks each",
                    //label = "% of tasks among them",
                    axisLabel = true,
                    //usePercentageBasedAmongData = true,
                    axisOffset = (-40).dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp)
                )
                HorizontalDivider()
            }
        }

        item {
            val points = mapOf(
                "1" to 14f,
                "2" to 37f,
                "3" to 25f,
                "4" to 52f,
                "5" to 41f,
            )

            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Tasks Done per Difficulty",
                        fontSize = 14f.sp
                    )
                }
                LineChartView(
                    dataPoints = points,
                    yAxisLabel = "Tasks",
                    xAxisLabel = "Difficulty",
                    axisLabel = true,
                    axisOffset = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp)
                )
                HorizontalDivider()
            }
        }

        item {
            val chartData = listOf(
                DonutOrPizzaChartData(
                    label = "Operações",
                    value = 4f,
                    value2 = 6f,
                    color = "#FBA0E3".toColorInt(),
                ),
                DonutOrPizzaChartData(
                    label = "TI",
                    value = 3f,
                    value2 = 7f,
                    color = NotComposeColor.GREEN,
                ),
                DonutOrPizzaChartData(
                    label = "RH",
                    value = 2f,
                    value2 = 5f,
                    color = NotComposeColor.BLUE,
                ),
                DonutOrPizzaChartData(
                    label = "Marketing",
                    value = 1f,
                    value2 = 10f,
                    color = "#AE794B".toColorInt(),
                )
            )

            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Tasks Done per Department",
                        fontSize = 14f.sp
                    )
                }
                PizzaChartView(
                    data = chartData,
                    showComparisonWithDataSum = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp)
                )
                HorizontalDivider()
            }
        }

        item {
            val donutChartData = listOf(
                DonutOrPizzaChartData(
                    label = "John",
                    value = 4f,
                    color = "#ffa500".toColorInt(),
                ),
                DonutOrPizzaChartData(
                    label = "Jessica",
                    value = 3f,
                    color = "#800080".toColorInt(),
                ),
                DonutOrPizzaChartData(
                    label = "Alfred",
                    value = 5f,
                    color = NotComposeColor.RED,
                ),
                DonutOrPizzaChartData(
                    label = "Ashley",
                    value = 1f,
                    color = NotComposeColor.YELLOW,
                ),
                DonutOrPizzaChartData(
                    label = "Bob",
                    value = 7f,
                    color = NotComposeColor.GRAY,
                ),
            )

            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Tasks Done per Employee",
                        fontSize = 14f.sp
                    )
                }
                DonutChartView(
                    data = donutChartData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp) // or use size(250.dp) for square
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ChartsSamplesScreenPreview() {
    FiapChallangePreviewTheme {
        ChartsSamplesScreen()
    }
}
