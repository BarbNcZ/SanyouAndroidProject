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
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult

@Composable
fun CreateFuncionarioScreen(
    criarFuncionario: (Int, Int, String, String) -> Unit,
    idFuncionario: Int,
    departamentos: List<GetDepartamentoResult>,
    cargos: List<GetCargoResult>
) {

    val context = LocalContext.current

    var idCargo by remember {
        mutableStateOf(-1)
    }

    var idDepto by remember {
        mutableStateOf(-1)
    }

    var email by remember {
        mutableStateOf("")
    }

    var nmFuncionario by remember {
        mutableStateOf("")
    }

    Text(
        text = "Resultado ${idFuncionario}"
    )

    Text(
        text = "Email: "
    )

    TextField(email, {email = it})


    Text(
        text = "Nome do Funcionario: "
    )

    TextField(nmFuncionario, {nmFuncionario = it})

    CargosDropDownMenu(

        selectedValue = "Nenhum",
        options = cargos,
        label = "Cargo",
        { cdCargo ->
            idCargo = cdCargo
        }

    )

    DeptosDropDownMenu(

        selectedValue = "Nenhum",
        options = departamentos,
        label = "Departamento",
        { cdDepartamento ->
            idDepto = cdDepartamento
        }

    )

    //botao de criar tarefa
    Button({

        if (idCargo == -1){
            Toast.makeText(context, "Escolha um cargo", Toast.LENGTH_SHORT).show()
        }

        else if (idDepto == -1){
            Toast.makeText(context, "Escolha um departamento", Toast.LENGTH_SHORT).show()
        }

        else{
            criarFuncionario(idDepto, idCargo, email, nmFuncionario)
        }
    }
    ) {
        Text("Criar Funcionario")
    }
}