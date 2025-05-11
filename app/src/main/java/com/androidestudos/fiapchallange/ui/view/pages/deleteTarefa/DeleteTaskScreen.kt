package com.androidestudos.fiapchallange.ui.view.pages.deleteTarefa

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme
import com.androidestudos.fiapchallange.ui.view.atoms.TasksDropDownMenu
import com.androidestudos.fiapchallange.ui.view.pages.createTarefa.CreateTaskScreen

@Composable
fun DeleteTaskScreen(
    deleteTask: (Int) -> Unit,
    tasks: List<GetTarefasResult>
){

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 30.dp, end = 30.dp)
        ) {
            val context = LocalContext.current

            var taskId by remember {
                mutableStateOf(-1)
            }

            var selected = remember { mutableStateOf("Nenhuma") }

            TasksDropDownMenu(
                modifier = Modifier.fillMaxWidth(),
                selectedValue = selected,
                options = tasks,
                label = "Tarefas",
                onValueChangedEvent = { cdTarefa ->
                    taskId = cdTarefa
                }
            )

            Box(modifier = Modifier.fillMaxWidth().padding(top = 25.dp)) {
                //botao de excluir tarefa
                Button(
                    modifier = Modifier
                        .align(Alignment.Center),
                    onClick = {
                        if (taskId == -1){
                            Toast.makeText(context, "Por favor escolha uma tarefa", Toast.LENGTH_SHORT).show()
                        }

                        else{
                            deleteTask(taskId)
                            taskId = -1
                            selected.value = "Nenhuma"
                        }
                    }
                ) {
                    Text("Excluir Tarefa")
                }
            }
        }
    }
}

@Preview
@Composable
fun DeleteTaskScreenPreview() {
    FiapChallangePreviewTheme {
        DeleteTaskScreen(
            { _ -> },
            emptyList(),
        )
    }
}