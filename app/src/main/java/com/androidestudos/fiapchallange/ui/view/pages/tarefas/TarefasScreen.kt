package com.androidestudos.fiapchallange.ui.view.pages.tarefas

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.data.GetTarefaResult

@Composable
fun TarefasScreen(
    tarefas: List<GetTarefaResult>
) {
    if (tarefas.isNotEmpty()){
        LazyColumn(Modifier.height(1080.dp)) {
            items(tarefas.size) { index ->
                val tarefa = tarefas[index]
                Text("${tarefa.dsTarefas} - ${tarefa.dsTipoTarefa}")
            }
        }
    }
    else{
        Text("Nenhuma tarefa para este funcionario")
    }
}