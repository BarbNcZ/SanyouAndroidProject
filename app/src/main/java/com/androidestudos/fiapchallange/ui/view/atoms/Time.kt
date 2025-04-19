package com.androidestudos.fiapchallange.ui.view.atoms

import android.app.TimePickerDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun Time(
    label: String = "Pick Time",
    initialHour: Int = 12,
    initialMinute: Int = 0,
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    val context = LocalContext.current
    var selectedTime by remember { mutableStateOf("$initialHour:${"%02d".format(initialMinute)}") }

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hour: Int, minute: Int ->
                selectedTime = "$hour:${"%02d".format(minute)}"
                onTimeSelected(hour, minute)
            },
            initialHour,
            initialMinute,
            true
        )
    }

    Button(onClick = { timePickerDialog.show() }) {
        Text("$label: $selectedTime")
    }
}