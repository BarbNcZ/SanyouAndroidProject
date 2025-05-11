package com.androidestudos.fiapchallange.ui.view.pages.createemployees

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme
import com.androidestudos.fiapchallange.ui.view.atoms.RolesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.DepartmentsDropDownMenu
import com.androidestudos.fiapchallange.ui.view.pages.createTarefa.CreateTaskScreen

@Composable
fun CreateEmployeeScreen(
    createEmployee: (Int, Int, String, String) -> Unit,
    departments: List<GetDepartamentoResult>,
    roles: List<GetCargoResult>
) {
    val context = LocalContext.current

    var roleId by remember {
        mutableStateOf(-1)
    }

    var departmentId by remember {
        mutableStateOf(-1)
    }

    var email by remember {
        mutableStateOf("")
    }

    var employeeName by remember {
        mutableStateOf("")
    }

    val configuration = LocalConfiguration.current
    val screenHeightPx = configuration.screenHeightDp.dp

    // Default TopAppBar height in Material is 64.dp for large screens or 56.dp for normal
    val topBarHeight = 56.dp

    val availableHeight = screenHeightPx - topBarHeight

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier
            .padding(start = 30.dp, end = 30.dp)
            .align(Alignment.TopCenter)
            .height(availableHeight)
        ) {

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = employeeName,
                    onValueChange = {employeeName = it},
                    placeholder = { Text("Nome do Funcionario") },
                )
            }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp),
                    value = email,
                    onValueChange = {email = it},
                    placeholder = { Text("Email") },
                )
            }

            item {
                RolesDropDownMenu(
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp),
                    selectedValue = "Nenhum",
                    options = roles,
                    label = "Cargo",
                    onValueChangedEvent = { cdCargo ->
                        roleId = cdCargo
                    }

                )
            }

            item {
                DepartmentsDropDownMenu(
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp),
                    selectedValue = "Nenhum",
                    options = departments,
                    label = "Departamento",
                    onValueChangedEvent = { cdDepartamento ->
                        departmentId = cdDepartamento
                    }

                )
            }

            item {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 25.dp)) {
                    //botao de criar tarefa
                    Button(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {
                            if (roleId == -1){
                                Toast.makeText(context, "Escolha um cargo", Toast.LENGTH_SHORT).show()
                            }

                            else if (departmentId == -1){
                                Toast.makeText(context, "Escolha um departamento", Toast.LENGTH_SHORT).show()
                            }

                            else{
                                createEmployee(departmentId, roleId, email, employeeName)
                            }
                        }
                    ) {
                        Text("Criar Funcionário")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateEmployeeScreenPreview() {
    FiapChallangePreviewTheme {
        CreateEmployeeScreen(
            { _, _, _, _ -> },
            emptyList(),
            emptyList(),
        )
    }
}