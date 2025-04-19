package com.androidestudos.fiapchallange.ui.view.pages.ranking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.data.GetFuncionarioResult

@Composable
fun RankingScreen (
    funcionarios: List<GetFuncionarioResult>
){
    if (funcionarios.isNotEmpty()){
        LazyColumn (Modifier.height(1080.dp)
            .fillMaxWidth()


        ) {

            val usedColors = mutableListOf<Int>()

            items(funcionarios.size){ index ->
                val funcionario = funcionarios[index]

                var colorIndex = (0..9).random()

                while (usedColors.contains(colorIndex)){

                    colorIndex = (0..9).random()

                }

                usedColors.add(colorIndex)

                Box(Modifier.fillMaxWidth()){

                    Text(
                        modifier = Modifier.align(Alignment.Center),

                        color = when(colorIndex){
                            0 -> Color(android.graphics.Color.parseColor("#9d13b2"))
                            1 -> Color(android.graphics.Color.parseColor("#0e4913"))
                            2 -> Color(android.graphics.Color.parseColor("#13265d"))
                            3 -> Color(android.graphics.Color.parseColor("#811f14"))
                            4 -> Color(android.graphics.Color.parseColor("#736807"))
                            5 -> Color(android.graphics.Color.parseColor("#07735a"))
                            6 -> Color.Black
                            7 -> Color(android.graphics.Color.parseColor("#565356"))
                            8 -> Color(android.graphics.Color.parseColor("#a86009"))
                            else -> Color(android.graphics.Color.parseColor("#5524a3"))
                        } ,

                        text = "${funcionario.nmFuncionario}"
                    )
                }
            }
        }
    }
    else{
        Text("Nenhum Ranking encontrado")
    }
}