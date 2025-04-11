package com.androidestudos.fiapchallange.ui.view.pages.tarefas

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.data.GetTarefaResult

@Composable
fun TasksScreen(
    tasks: List<GetTarefaResult>
) {
    if (tasks.isNotEmpty()){
        LazyColumn(Modifier.height(1080.dp)) {
            items(tasks.size) { index ->
                val task = tasks[index]
                Text("${task.dsTarefas} - ${task.dsTipoTarefa}")
            }
        }
    }
    else{
        Text("Nenhuma tarefa para este funcionario")
    }
}