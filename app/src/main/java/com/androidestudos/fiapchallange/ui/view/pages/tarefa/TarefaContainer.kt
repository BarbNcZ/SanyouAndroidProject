package com.androidestudos.fiapchallange.ui.view.pages.tarefa

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.Route
import com.androidestudos.fiapchallange.ui.models.TarefasEvents
import com.androidestudos.fiapchallange.ui.view.pages.employees.EmployeesScreen
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun TarefaContainer(
    viewModel: TarefasViewModel = koinViewModel(),
    navHostController: NavHostController,

){

    val state = viewModel.state.collectAsStateWithLifecycle()


    TarefaScreen(
        ""
    )
}