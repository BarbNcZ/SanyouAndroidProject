package com.androidestudos.fiapchallange.ui.view.pages.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.androidestudos.fiapchallange.R
import com.androidestudos.fiapchallange.ui.theme.FiapChallangePreviewTheme

@Composable
fun MenuScreen(
    goToEmployeesScreen: () -> Unit,
    goToCreateTaskScreen: () -> Unit,
    goToDeleteTaskScreen: () -> Unit,
    goToDeleteFuncionarioScreen: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 70.dp)
                .align(Alignment.TopCenter)
        ) {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color("#EAFFDE".toColorInt()),
                    contentColor = Color.Black,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 15.dp
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    goToCreateTaskScreen()
                }
            ) {
                Icon(
                    modifier = Modifier.size(25.dp).padding(end = 5.dp),
                    painter = painterResource(R.drawable.create_task_button_icon),
                    contentDescription = null
                )
                Text("Criar Novas Tarefas")
            }

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
                colors = ButtonColors(
                    containerColor = Color("#FFDBDB".toColorInt()),
                    contentColor = Color.Black,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 15.dp
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    goToDeleteTaskScreen()
                }
            ) {
                Icon(
                    modifier = Modifier.size(25.dp).padding(end = 5.dp),
                    painter = painterResource(R.drawable.delete_task_button_icon),
                    contentDescription = null
                )
                Text("Deletar Tarefa")
            }

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
                colors = ButtonColors(
                    containerColor = Color("#DAE0FF".toColorInt()),
                    contentColor = Color.Black,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 15.dp
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    goToEmployeesScreen()
                }
            ) {
                Icon(
                    modifier = Modifier.size(25.dp).padding(end = 5.dp),
                    painter = painterResource(R.drawable.create_employee_button_icon),
                    contentDescription = null
                )
                Text("Criar Novo Funcionario")
            }

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
                colors = ButtonColors(
                    containerColor = Color("#FFDCDC".toColorInt()),
                    contentColor = Color.Black,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 15.dp
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    goToDeleteFuncionarioScreen()
                }
            ) {
                Icon(
                    modifier = Modifier.size(25.dp).padding(end = 5.dp),
                    painter = painterResource(R.drawable.delete_employee_button_icon),
                    contentDescription = null
                )
                Text("Excluir Funcionario")
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    FiapChallangePreviewTheme {
        MenuScreen(
            { },
            { },
            { },
            { },
        )
    }
}