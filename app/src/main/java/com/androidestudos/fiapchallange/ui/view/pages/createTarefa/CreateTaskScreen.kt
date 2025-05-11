package com.androidestudos.fiapchallange.ui.view.pages.createTarefa

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme
import com.androidestudos.fiapchallange.ui.view.atoms.EmployeesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.FiveStars
import com.androidestudos.fiapchallange.ui.view.atoms.TaskTypesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.Time
import java.util.Calendar

@Composable
fun CreateTaskScreen(
    createTask: (Int, Int, String, Int, Long) -> Unit,
    taskTypes: List<GetTipoTarefaResult>,
    employees: List<GetFuncionarioResult>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 30.dp, end = 30.dp)
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

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = taskName,
                onValueChange = {taskName = it},
                placeholder = {
                    Text("Descrição da Tarefa")
                }
            )

            val estimation = remember { mutableIntStateOf(0) }
            val hour = remember { mutableIntStateOf(0) }
            val minute = remember { mutableIntStateOf(0) }


            Box(modifier = Modifier.fillMaxWidth().padding(top = 15.dp)) {
                FiveStars(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {

                    estimation.intValue = it

                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Nível de Dificuldade da Tarefa"
                )
            }


            Box(modifier = Modifier.fillMaxWidth().padding(top = 25.dp)) {
                Time(
                    modifier = Modifier
                        .align(Alignment.Center),
                    label = "Estimativa",
                    onTimeSelected = { _hour, _minute ->
                        hour.intValue = _hour
                        minute.intValue = _minute
                    }
                )
            }

            TaskTypesDropDownMenu(
                modifier = Modifier.padding(top = 30.dp),
                selectedValue = "Nenhum",
                options = taskTypes,
                label = "Tipo de Tarefa",
                onValueChangedEvent = { cdTipoTarefa ->
                    taskTypeId = cdTipoTarefa
                }

            )


            var selected = remember { mutableStateOf("Nenhuma") }


            EmployeesDropDownMenu(
                modifier = Modifier.padding(top = 30.dp),
                selectedValue = selected,
                options = employees,
                label = "Funcionario",
                onValueChangedEvent = { cdFuncionario ->
                    employeeId = cdFuncionario
                }
            )

            Box(modifier = Modifier.fillMaxWidth()) {
            //botao de criar tarefa
                Button(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 25.dp),
                    onClick = {
                        if (taskTypeId == -1 || employeeId == -1 || estimation.intValue < 10 || (minute.intValue < 30 && hour.intValue == 0)) {
                            Toast.makeText(
                                context,
                                """
                                Por favor escolha um tipo de tarefa, um funcionario,
                                uma estimativa minima de 1 estrela e um tempo minimo de 30 minutos
                                """.trimIndent(),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
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
        }
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

@Preview
@Composable
fun CreateTaskScreenPreview() {
    FiapChallangePreviewTheme {
        CreateTaskScreen(
            { _, _, _, _, _ -> },
            emptyList(),
            emptyList(),
        )
    }
}