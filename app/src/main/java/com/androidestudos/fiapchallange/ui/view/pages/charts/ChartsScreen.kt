package com.androidestudos.fiapchallange.ui.view.pages.charts

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.androidestudos.fiapchallange.data.GetChartTaskPerDepartmentResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerDifficultyResult
import com.androidestudos.fiapchallange.data.GetChartTaskPerRoleResult
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.ui.models.BarsChartData
import com.androidestudos.fiapchallange.ui.models.DonutOrPizzaChartData
import android.graphics.Color as NotComposeColor
import com.androidestudos.fiapchallange.ui.view.atoms.charts.DonutChartView
import com.androidestudos.fiapchallange.ui.view.atoms.charts.HorizontalBarsChartView
import com.androidestudos.fiapchallange.ui.view.atoms.charts.LineChartView
import com.androidestudos.fiapchallange.ui.view.atoms.charts.VerticalBarsChartView

@Composable
fun ChartsScreen (
    dataPointsPerEmployee: List<GetFuncionarioResult>,
    dataTasksPerRole: List<GetChartTaskPerRoleResult>,
    dataTasksPerDepartment: List<GetChartTaskPerDepartmentResult>,
    dataTasksPerDifficulty: List<GetChartTaskPerDifficultyResult>

){
    LazyColumn (Modifier.height(1080.dp).padding(bottom = 150.dp)
        .fillMaxWidth()

    ) {
        item {
            val dataHorizontalChart = dataPointsPerEmployee.map {
                BarsChartData(
                    label = it.nmFuncionario,
                    value = it.nrPontos.toFloat()
                )
            }


            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Top 5 Points per Employee",
                        fontSize = 14f.sp
                    )
                }
                HorizontalBarsChartView(
                    data = dataHorizontalChart,
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
            val dataColumnChart = dataTasksPerRole.map {
                BarsChartData(
                    label = it.dsCargo,
                    value = it.qtdTarefas.toFloat(),
                    value2 = it.qtdTarefasTotal.toFloat()
                )
            }

            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Tasks Done per Role",
                        fontSize = 14f.sp
                    )
                }
                VerticalBarsChartView(
                    data = dataColumnChart,
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

//        item {
//            val dataLinearChart = dataTasksPerDifficulty.map {
//                it.nrDificuldade.toString() to it.qtdTarefas.toFloat()
//            }.toMap()
//
//            Column {
//                Box(Modifier.fillMaxWidth()) {
//                    Text(
//                        modifier = Modifier.align(Alignment.Center),
//                        text = "Tasks Done per Difficulty",
//                        fontSize = 14f.sp
//                    )
//                }
//                LineChartView(
//                    dataPoints = dataLinearChart,
//                    yAxisLabel = "Tasks",
//                    xAxisLabel = "Difficulty",
//                    axisLabel = true,
//                    axisOffset = 2.dp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(300.dp)
//                        .padding(16.dp)
//                )
//                HorizontalDivider()
//            }
//        }

        item {
            val dataDonutChart = dataTasksPerDepartment.map {
                DonutOrPizzaChartData(
                    label = it.nmDepto,
                    value = it.qtdTarefas.toFloat(),
                    value2 = it.qtdTarefasTotal.toFloat()
                )
            }

            Column {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Tasks Done per Department",
                        fontSize = 14f.sp
                    )
                }
                DonutChartView(
                    data = dataDonutChart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp) // or use size(250.dp) for square
                        .padding(16.dp)
                )
            }
        }
    }
}