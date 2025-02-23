package com.androidestudos.fiapchallange.ui.view

import androidx.compose.runtime.Composable
import com.androidestudos.fiapchallange.data.GetCargoResult
import com.androidestudos.fiapchallange.data.GetDepartamentoResult

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