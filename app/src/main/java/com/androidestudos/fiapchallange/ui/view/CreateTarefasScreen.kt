package com.androidestudos.fiapchallange.ui.view

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

@Composable
fun CreateTarefasScreen(
    criarTarefa: (Int, String) -> Unit,
    idTarefa: Int,
    tiposTarefa: List<GetTipoTarefaResult>
) {

    val context = LocalContext.current

    var idTipoTarefa by remember {
        mutableStateOf(-1)
    }

    var text by remember {
        mutableStateOf("")
    }

    Text(
        text = "Resultado ${idTarefa}"
    )

    TextField(text, {text = it})

    TiposTarefaDropDownMenu(

        selectedValue = "Nenhum",
        options = tiposTarefa,
        label = "Tipo de Tarefa",
        { cdTipoTarefa ->
            idTipoTarefa = cdTipoTarefa
        }

    )

    //botao de criar tarefa
    Button({

        if (idTipoTarefa == -1){
            Toast.makeText(context, "Por favor escolha um tipo de tarefa", Toast.LENGTH_SHORT).show()
        }

        else{
            criarTarefa(idTipoTarefa, text)
        }
    }
    ) {
        Text("Criar Tarefa")
    }
}