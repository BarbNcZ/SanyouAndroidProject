package com.androidestudos.fiapchallange.ui.view.pages.tarefas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.elevatedCardElevation
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.R
import com.androidestudos.fiapchallange.data.GetTarefaResult
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme
import com.androidestudos.fiapchallange.ui.view.pages.menu.MenuScreen
import com.google.gson.annotations.SerializedName

@Composable
fun TasksScreen(
    cdFuncionario: Int,
    tasks: List<GetTarefaResult>,
    concludeTask: (Int, Int) -> Unit
) {
    if (tasks.isNotEmpty()){

        var showDialog by remember { mutableStateOf(false) }
        var currentTask by remember { mutableIntStateOf(-1) }
        var isEnabled by remember { mutableStateOf(Pair(-1, true)) }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(Modifier
                .padding(start = 30.dp, end = 30.dp)
                .align(Alignment.TopCenter)
                .height(1080.dp),
            ) {
                items(tasks.size) { index ->
                    val task = tasks[index]

                    var isChecked by remember { mutableStateOf(task.isConcluded()) }

                    ElevatedCard(
                        modifier = Modifier
                            .padding(
                                top =
                                    if (index > 0)
                                        20.dp
                                    else
                                        0.dp
                            ),
                        elevation = elevatedCardElevation(defaultElevation = 15.dp)
                    ) {
                        Column (
                            modifier = Modifier
                                .padding(
                                    start = 10.dp,
                                )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Text(
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    text = "${task.dsTarefas} - ${task.dsTipoTarefa}"
                                )
                                Row(
                                    modifier = Modifier.align(Alignment.CenterEnd)
                                ) {
                                    Box(
                                        modifier = Modifier.height(50.dp)
                                    ){
                                        Text(

                                            modifier = Modifier.align(Alignment.Center),

                                            text = when(task.nrDificuldade - (50*(task.minute/30).toInt()) - (100*task.hour)) {

                                                10 -> "1"
                                                25 -> "2"
                                                50 -> "3"
                                                75 -> "4"
                                                100 -> "5"

                                                else -> "0"
                                            }
                                        )
                                    }
                                    Image(
                                        painterResource(R.drawable.highlighted_star),
                                        modifier = Modifier.size(50.dp),
                                        contentDescription = ""
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Text(
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    text = "Tempo: ${task.hour}h${task.minute}m"
                                )
                                Checkbox(

                                    modifier = Modifier.align(Alignment.CenterEnd),

                                    enabled = if (task.cdTarefas == isEnabled.first){
                                        isEnabled.second
                                    }
                                    else{
                                        if (!isChecked){
                                            task.isConcluded().not()
                                        }
                                        else{
                                            false
                                        }
                                    },

                                    checked = if (task.cdTarefas == isEnabled.first) {
                                        !isEnabled.second
                                    }else{
                                        isChecked
                                    }
                                    ,
                                    onCheckedChange = {
                                        isChecked = it

                                        showDialog = true

                                        currentTask = task.cdTarefas
                                    }
                                )
                            }
                        }
                    }
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Você tem certeza?") },
                    text = { Text("Você confirma a conclusão desta tarefa?") },
                    confirmButton = {
                        TextButton(onClick = {
                            concludeTask(currentTask, cdFuncionario)
                            showDialog = false

                            isEnabled = currentTask to false
                        }) {
                            Text("Sim")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                            isEnabled = currentTask to true
                        }) {
                            Text("Não")
                        }
                    }
                )
            }
        }
    }
    else{
        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier
                .padding(start = 30.dp, end = 30.dp)
                .align(Alignment.TopCenter)
            ) {
                Text("Nenhuma tarefa para este funcionario")
            }
        }
    }
}

@Preview
@Composable
fun TasksScreenPreview() {
    FiapChallangePreviewTheme {
        TasksScreen(
            -1,
            listOf(
                GetTarefaResult(
                    cdTarefas = -1,
                    dsTarefas = "Task 1",
                    dsTipoTarefa = "Task Type",
                    nrTempo = 1745022600000,
                    nrDificuldade = 100,
                    btFinalizado = 0,
                ),
                GetTarefaResult(
                    cdTarefas = -1,
                    dsTarefas = "Task 2",
                    dsTipoTarefa = "Task Type",
                    nrTempo = 1745022600000,
                    nrDificuldade = 100,
                    btFinalizado = 1,
                ),
            ),
            { _, _ -> },
        )
    }
}

@Preview
@Composable
fun TasksScreenWithoutTasksPreview() {
    FiapChallangePreviewTheme {
        TasksScreen(
            -1,
            emptyList(),
            { _, _ -> },
        )
    }
}