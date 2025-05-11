package com.androidestudos.fiapchallange.ui.view.atoms

import android.app.TimePickerDialog as AndroidTimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerDialogDefaults
import androidx.compose.material3.TimePickerDisplayMode
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidestudos.fiapchallange.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Time(
    modifier: Modifier = Modifier,
    label: String = "Pick Time",
    initialHour: Int = 0,
    initialMinute: Int = 30,
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    val context = LocalContext.current
    var selectedTime by remember { mutableStateOf("${initialHour}h${"%02d".format(initialMinute)}m") }

    val timePickerDialog = remember {
        AndroidTimePickerDialog(
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

    /*TimeInput(
        modifier = Modifier.clickable {
            timePickerDialog.show()
        },
        state = rememberTimePickerState()
    )*/
    Button(modifier = modifier, onClick = { timePickerDialog.show() }) {
        Icon(
            modifier = Modifier.size(25.dp).padding(end = 5.dp),
            painter = painterResource(R.drawable.time_estimation_button_icon),
            contentDescription = null
        )
        Text("$label: $selectedTime")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TimeInputSample() {
    var showTimePicker by remember { mutableStateOf(false) }
    val state = rememberTimePickerState()
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    Box(propagateMinConstraints = false) {
        Button(modifier = Modifier.align(Alignment.Center), onClick = { showTimePicker = true }) {
            Text("Set Time")
        }
        SnackbarHost(hostState = snackState)
    }

    if (showTimePicker) {
        TimePickerDialog(
            title = { TimePickerDialogDefaults.Title(displayMode = TimePickerDisplayMode.Input) },
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.HOUR_OF_DAY, state.hour)
                        cal.set(Calendar.MINUTE, state.minute)
                        cal.isLenient = false
                        snackScope.launch {
                            snackState.showSnackbar("Entered time: ${formatter.format(cal.time)}")
                        }
                        showTimePicker = false
                    }
                ) {
                    Text("Ok")
                }
            },
            dismissButton = { TextButton(onClick = { showTimePicker = false }) { Text("Cancel") } },
            modeToggleButton = {},
        ) {
            TimeInput(state = state)
        }
    }
}