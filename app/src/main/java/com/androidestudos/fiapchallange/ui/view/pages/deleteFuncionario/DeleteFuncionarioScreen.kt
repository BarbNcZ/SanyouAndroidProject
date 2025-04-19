package com.androidestudos.fiapchallange.ui.view.pages.deleteFuncionario

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.ui.view.atoms.EmployeesDropDownMenu
import com.androidestudos.fiapchallange.ui.view.atoms.TasksDropDownMenu

@Composable
fun DeleteFuncionarioScreen(
    deleteFuncionario: (Int) -> Unit,
    funcionarios: List<GetFuncionarioResult>
){

    val context = LocalContext.current

    var funcionarioId by remember {
        mutableStateOf(-1)
    }

    var selected = remember { mutableStateOf("Nenhuma") }

    EmployeesDropDownMenu(
        selectedValue = selected,
        options = funcionarios,
        label = "Funcionarios",
        { cdFuncionario ->
            funcionarioId = cdFuncionario
        }
    )

    //botao de excluir Funcionario
    Button({

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
        Text("Deletar Funcionario")
    }
}