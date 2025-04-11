package com.androidestudos.fiapchallange.ui.view.pages.deleteTarefa

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.ui.view.atoms.TasksDropDownMenu

@Composable
fun DeleteTaskScreen(
    deleteTask: (Int) -> Unit,
    tasks: List<GetTarefasResult>
){

    val context = LocalContext.current

    var taskId by remember {
        mutableStateOf(-1)
    }

    var selected = remember { mutableStateOf("Nenhuma") }

    TasksDropDownMenu(
        selectedValue = selected,
        options = tasks,
        label = "Tarefas",
        { cdTarefa ->
            taskId = cdTarefa
        }
    )

    //botao de excluir tarefa
    Button({

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
        Text("Deletar Tarefa")
    }
}