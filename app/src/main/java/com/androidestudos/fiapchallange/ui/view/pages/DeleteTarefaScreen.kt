package com.androidestudos.fiapchallange.ui.view.pages

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
import com.androidestudos.fiapchallange.ui.view.atoms.TarefasDropDownMenu

@Composable
fun DeleteTarefaScreen(
    deleteTarefa: (Int) -> Unit,
    tarefas: List<GetTarefasResult>
){

    val context = LocalContext.current

    var idTarefa by remember {
        mutableStateOf(-1)
    }

    var selected = remember { mutableStateOf("Nenhuma") }

    TarefasDropDownMenu(
        selectedValue = selected,
        options = tarefas,
        label = "Tarefas",
        { cdTarefa ->
            idTarefa = cdTarefa
        }
    )

    //botao de excluir tarefa
    Button({

        if (idTarefa == -1){
            Toast.makeText(context, "Por favor escolha uma tarefa", Toast.LENGTH_SHORT).show()
        }

        else{
            deleteTarefa(idTarefa)
            idTarefa = -1
            selected.value = "Nenhuma"
        }
    }
    ) {
        Text("Deletar Tarefa")
    }
}