package com.androidestudos.fiapchallange.ui.view.pages.createTarefa

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult
import com.androidestudos.fiapchallange.ui.view.atoms.EmployeesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.FiveStars
import com.androidestudos.fiapchallange.ui.view.atoms.TaskTypesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.Time
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar

@Composable
fun CreateTaskScreen(
    createTask: (Int, Int, String, Int, Long) -> Unit,
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

    var taskName by remember {
        mutableStateOf("")
    }

    Text(
        text = "Resultado ${taskId}"
    )

    TextField(taskName, {taskName = it})

    val estimation = remember { mutableIntStateOf(0) }
    val hour = remember { mutableIntStateOf(0) }
    val minute = remember { mutableIntStateOf(0) }


    FiveStars() {

        estimation.intValue = it

    }


    Time(
        label = "Tempo",
        onTimeSelected = { _hour, _minute ->
            hour.intValue = _hour
            minute.intValue = _minute
        }
    )


    TaskTypesDropDownMenu(

        selectedValue = "Nenhum",
        options = taskTypes,
        label = "Tipo de Tarefa",
        { cdTipoTarefa ->
            taskTypeId = cdTipoTarefa
        }

    )


    var selected = remember { mutableStateOf("Nenhuma") }


    EmployeesDropDownMenu(

        selectedValue = selected,
        options = employees,
        label = "Funcionario",
        { cdFuncionario ->
            employeeId = cdFuncionario
        }
    )

    //botao de criar tarefa
    Button({

        if (taskTypeId == -1 || employeeId == -1 || estimation.intValue < 10 || (minute.intValue < 30 && hour.intValue == 0)) {
            Toast.makeText(context, """
                Por favor escolha um tipo de tarefa, um funcionario,
                uma estimativa minima de 1 estrela e um tempo minimo de 30 minutos
                """.trimIndent(), Toast.LENGTH_SHORT).show()
        }

        else{
            createTask(
                taskTypeId,
                employeeId,
                taskName,
                estimation.intValue + (50*(minute.intValue/30).toInt()) + (100*hour.intValue),
                toTimestamp(hour.intValue, minute.intValue)
            )
        }
    }
    ) {
        Text("Criar Tarefa")
    }
}


fun toTimestamp(hour: Int, minute: Int): Long {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.timeInMillis
}