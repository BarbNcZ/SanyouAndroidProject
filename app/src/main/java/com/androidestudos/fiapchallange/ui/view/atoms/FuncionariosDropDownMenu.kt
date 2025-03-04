package com.androidestudos.fiapchallange.ui.view.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuncionariosDropDownMenu(
    selectedValue: String,
    options: List<GetFuncionarioResult>,
    label: String,
    onValueChangedEvent: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(selectedValue) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selected,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { funcionario: GetFuncionarioResult ->
                DropdownMenuItem(
                    text = { Text(text = "${funcionario.nmFuncionario} (${funcionario.dsCargo} - ${funcionario.nmDepto})") },
                    onClick = {
                        expanded = false
                        selected = "${funcionario.nmFuncionario} (${funcionario.dsCargo} - ${funcionario.nmDepto})"
                        onValueChangedEvent(funcionario.cdFuncionario)
                    }
                )
            }
        }
    }
}