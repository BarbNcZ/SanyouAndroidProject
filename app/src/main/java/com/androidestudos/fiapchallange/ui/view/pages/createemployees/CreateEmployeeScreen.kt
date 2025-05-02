package com.androidestudos.fiapchallange.ui.view.pages.createemployees

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult
import com.androidestudos.fiapchallange.ui.view.atoms.RolesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.DepartmentsDropDownMenu

@Composable
fun CreateEmployeeScreen(
    createEmployee: (Int, Int, String, String) -> Unit,
    employeeId: Int,
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

    LazyColumn(Modifier.height(availableHeight)) {
        item {
            Text(
                text = "Resultado $employeeId"
            )
        }

        item {
            Text(
                text = "Email: "
            )
        }

        item {
            TextField(email, {email = it})
        }

        item {
            Text(
                text = "Nome do Funcionario: "
            )
        }

        item {
            TextField(employeeName, {employeeName = it})
        }

        item {
            RolesDropDownMenu(

                selectedValue = "Nenhum",
                options = roles,
                label = "Cargo",
                { cdCargo ->
                    roleId = cdCargo
                }

            )
        }

        item {
            DepartmentsDropDownMenu(

                selectedValue = "Nenhum",
                options = departments,
                label = "Departamento",
                { cdDepartamento ->
                    departmentId = cdDepartamento
                }

            )
        }

        item {
            //botao de criar tarefa
            Button({

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
                Text("Criar Funcionario")
            }
        }
    }
}