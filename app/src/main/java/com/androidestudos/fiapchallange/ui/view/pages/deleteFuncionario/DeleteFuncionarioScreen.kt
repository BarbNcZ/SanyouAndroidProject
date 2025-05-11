package com.androidestudos.fiapchallange.ui.view.pages.deleteFuncionario

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
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme
import com.androidestudos.fiapchallange.ui.view.atoms.EmployeesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.TasksDropDownMenu
import com.androidestudos.fiapchallange.ui.view.pages.createemployees.CreateEmployeeScreen

@Composable
fun DeleteFuncionarioScreen(
    deleteFuncionario: (Int) -> Unit,
    funcionarios: List<GetFuncionarioResult>
){

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 30.dp, end = 30.dp)
        ) {
            val context = LocalContext.current

            var funcionarioId by remember {
                mutableStateOf(-1)
            }

            var selected = remember { mutableStateOf("Nenhuma") }

            EmployeesDropDownMenu(
                modifier = Modifier.fillMaxWidth(),
                selectedValue = selected,
                options = funcionarios,
                label = "Funcionarios",
                onValueChangedEvent = { cdFuncionario ->
                    funcionarioId = cdFuncionario
                }
            )

            Box(modifier = Modifier.fillMaxWidth().padding(top = 25.dp)) {
                //botao de excluir Funcionario
                Button(
                    modifier = Modifier
                        .align(Alignment.Center),
                    onClick = {

                        if (funcionarioId == -1){
                            Toast.makeText(context, "Por favor escolha uma Funcionario", Toast.LENGTH_SHORT).show()
                        }

                        else{
                            deleteFuncionario(funcionarioId)
                            funcionarioId = -1
                            selected.value = "Nenhum"
                        }
                    }
                ) {
                    Text("Excluir Funcionário")
                }
            }
        }
    }
}

@Preview
@Composable
fun DeleteFuncionarioScreenPreview() {
    FiapChallangePreviewTheme {
        DeleteFuncionarioScreen(
            { _ -> },
            emptyList(),
        )
    }
}