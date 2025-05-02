package com.androidestudos.fiapchallange.ui.view.pages.ranking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.ui.models.DonutOrPizzaChartData
import com.androidestudos.fiapchallange.ui.view.atoms.charts.PizzaChartView

@Composable
fun RankingScreen (
    funcionarios: List<GetFuncionarioResult>
){
    if (funcionarios.isNotEmpty()){
        LazyColumn (Modifier.height(1080.dp)
            .fillMaxWidth()

        ) {

            val usedColors = mutableListOf<Int>()



            val pizzaChartData = funcionarios.map {

                var colorIndex = (0..9).random()

                while (usedColors.contains(colorIndex)){

                    colorIndex = (0..9).random()

                }

                usedColors.add(colorIndex)

                DonutOrPizzaChartData(
                    label = it.nmFuncionario,
                    value = it.nrPontos.toFloat(),
                    color = when (colorIndex) {
                        0 -> android.graphics.Color.parseColor("#9d13b2")
                        1 -> android.graphics.Color.parseColor("#0e4913")
                        2 -> android.graphics.Color.parseColor("#13265d")
                        3 -> android.graphics.Color.parseColor("#811f14")
                        4 -> android.graphics.Color.parseColor("#736807")
                        5 -> android.graphics.Color.parseColor("#07735a")
                        6 -> Color.Black.toArgb()
                        7 -> android.graphics.Color.parseColor("#565356")
                        8 -> android.graphics.Color.parseColor("#a86009")
                        else -> android.graphics.Color.parseColor("#5524a3")
                    }
                )
            }


            item{
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Gráfico de Pontos",
                            fontSize = 14f.sp
                        )
                    }
                    PizzaChartView(
                        data = pizzaChartData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(16.dp)
                    )
                    HorizontalDivider()
                }
            }


            items(funcionarios.size){ index ->
                val funcionario = funcionarios[index]



                Column {

                    Row() {

                        Box(modifier = Modifier.weight(2f)) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                imageVector = Icons.Default.Person,
                                contentDescription = "Avatar"
                            )
                        }

                        Box(modifier = Modifier.weight(6f)) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                color = Color(pizzaChartData[index].color),
                                text = "${funcionario.nmFuncionario}",
                                fontSize = 19.sp
                            )
                        }

                        Box(modifier = Modifier.weight(2f)) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = "${funcionario.nrPontos}",
                                fontSize = 19.sp
                            )
                        }
                    }
                    HorizontalDivider()
                }
            }
        }
    }
    else{
        Text("Nenhum Ranking encontrado")
    }
}