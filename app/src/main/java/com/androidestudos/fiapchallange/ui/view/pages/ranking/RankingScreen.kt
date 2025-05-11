package com.androidestudos.fiapchallange.ui.view.pages.ranking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidestudos.fiapchallange.R
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.ui.models.DonutOrPizzaChartData
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme
import com.androidestudos.fiapchallange.ui.view.atoms.charts.PizzaChartView

@Composable
fun RankingScreen (
    funcionarios: List<GetFuncionarioResult>
){
    if (funcionarios.isNotEmpty()){
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(Modifier
                .padding(start = 30.dp, end = 30.dp)
                .align(Alignment.TopCenter)
                .height(1080.dp),
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

                item{
                    Text(
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                        text = "Leaderboard",
                        fontWeight = FontWeight.Bold
                    )
                }

                items(funcionarios.size){ index ->
                    val funcionario = funcionarios[index]

                    Column {

                        Row(
                            modifier =
                                Modifier.padding(
                                    top =
                                        if (index > 0)
                                            20.dp
                                        else
                                            0.dp
                                ),
                            ) {

                            Box(
                                modifier =
                                    Modifier.weight(1f)
                                        .align(Alignment.CenterVertically)
                            ) {
                                if (index < 3) {
                                    Icon(
                                        modifier =
                                            Modifier.size(
                                                when (index) {
                                                    0 -> 50.dp
                                                    1 -> 45.dp
                                                    else -> 40.dp
                                                }
                                            )
                                            .align(Alignment.Center),
                                        painter = painterResource(
                                            when (index) {
                                                0 -> R.drawable.gold_medal
                                                1 -> R.drawable.silver_medal
                                                else -> R.drawable.bronze_medal
                                            },
                                        ),
                                        tint= Color.Unspecified,
                                        contentDescription = null
                                    )
                                } else {
                                    Text(
                                        modifier =
                                            Modifier
                                                .align(Alignment.Center),
                                        text = (index + 1).toString(),
                                    )
                                }
                            }

                            Box(
                                modifier =
                                    Modifier.weight(2f)
                                        .align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    modifier = Modifier.align(Alignment.Center),
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Avatar"
                                )
                            }

                            Box(
                                modifier =
                                    Modifier.weight(5f)
                                        .align(Alignment.CenterVertically)
                            ) {
                                Text(
                                    color = Color(pizzaChartData[index].color),
                                    text = "${funcionario.nmFuncionario}",
                                    fontSize = 19.sp
                                )
                            }

                            Box(
                                modifier =
                                    Modifier.weight(2f)
                                        .align(Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = "${funcionario.nrPontos}",
                                    fontSize = 19.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    else{
        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier
                .padding(start = 30.dp, end = 30.dp)
                .align(Alignment.TopCenter)
            ) {
                Text("Nenhum Ranking encontrado")
            }
        }
    }
}

@Preview
@Composable
fun RankingScreenPreview() {
    FiapChallangePreviewTheme {
        RankingScreen(
            listOf(
                GetFuncionarioResult(
                    cdFuncionario = -1,
                    nmDepto = "Depto",
                    dsCargo = "Role",
                    nmFuncionario = "Employee 1",
                    nrPontos = 500,
                ),
                GetFuncionarioResult(
                    cdFuncionario = -1,
                    nmDepto = "Depto",
                    dsCargo = "Role",
                    nmFuncionario = "Employee 2",
                    nrPontos = 400,
                ),
                GetFuncionarioResult(
                    cdFuncionario = -1,
                    nmDepto = "Depto",
                    dsCargo = "Role",
                    nmFuncionario = "Employee 3",
                    nrPontos = 320,
                ),
                GetFuncionarioResult(
                    cdFuncionario = -1,
                    nmDepto = "Depto",
                    dsCargo = "Role",
                    nmFuncionario = "Employee 4",
                    nrPontos = 210,
                ),
                GetFuncionarioResult(
                    cdFuncionario = -1,
                    nmDepto = "Depto",
                    dsCargo = "Role",
                    nmFuncionario = "Employee 5",
                    nrPontos = 100,
                ),
            ),
        )
    }
}

@Preview
@Composable
fun RankingScreenWithNothingPreview() {
    FiapChallangePreviewTheme {
        RankingScreen(
            emptyList(),
        )
    }
}