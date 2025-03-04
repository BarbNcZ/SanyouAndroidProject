package com.androidestudos.fiapchallange.ui.view.pages.employees

import androidx.compose.runtime.Composable
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult
import com.androidestudos.fiapchallange.ui.view.pages.CreateFuncionarioScreen

@Composable
fun EmployeesScreen(

    idFuncionario: Int,
    createFuncionario: (Int, Int, String, String) -> Unit,
    deptos: List<GetDepartamentoResult>,
    cargos: List<GetCargoResult>

) {

    CreateFuncionarioScreen(
        createFuncionario,
        idFuncionario,
        deptos,
        cargos
    )

}