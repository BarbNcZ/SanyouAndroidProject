package com.androidestudos.fiapchallange.ui.view.pages.tarefas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.R
import com.androidestudos.fiapchallange.data.GetTarefaResult

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

        LazyColumn(Modifier.height(1080.dp)) {
            items(tasks.size) { index ->
                val task = tasks[index]

                var isChecked by remember { mutableStateOf(task.isConcluded()) }

                Row {
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
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        "Tempo: ${task.hour}:${task.minute}"
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
                HorizontalDivider()
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
    else{
        Text("Nenhuma tarefa para este funcionario")
    }
}