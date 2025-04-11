package com.androidestudos.fiapchallange.ui.view.pages.createTarefa

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
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult
import com.androidestudos.fiapchallange.ui.view.atoms.EmployeesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.TaskTypesDropDownMenu

@Composable
fun CreateTaskScreen(
    createTask: (Int, Int, String) -> Unit,
    taskId: Int,
    taskTypes: List<GetTipoTarefaResult>,
    employees: List<GetFuncionarioResult>
) {

    val context = LocalContext.current

    var taskTypeId by remember {
        mutableStateOf(-1)
    }

    var employeeId by remember {
        mutableStateOf(-1)
    }

    var text by remember {
        mutableStateOf("")
    }

    Text(
        text = "Resultado ${taskId}"
    )

    TextField(text, {text = it})

    TaskTypesDropDownMenu(

        selectedValue = "Nenhum",
        options = taskTypes,
        label = "Tipo de Tarefa",
        { cdTipoTarefa ->
            taskTypeId = cdTipoTarefa
        }

    )

    EmployeesDropDownMenu(

        selectedValue = "Nenhum",
        options = employees,
        label = "Funcionario",
        { cdFuncionario ->
            employeeId = cdFuncionario
        }

    )

    //botao de criar tarefa
    Button({

        if (taskTypeId == -1 || employeeId == -1 ) {
            Toast.makeText(context, "Por favor escolha um tipo de tarefa e um funcionario", Toast.LENGTH_SHORT).show()
        }

        else{
            createTask(taskTypeId, employeeId, text)
        }
    }
    ) {
        Text("Criar Tarefa")
    }
}