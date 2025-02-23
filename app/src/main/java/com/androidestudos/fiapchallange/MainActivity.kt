package com.androidestudos.fiapchallange

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult
import com.androidestudos.fiapchallange.ui.models.TarefasEvents
import com.androidestudos.fiapchallange.ui.models.TarefasState
import com.androidestudos.fiapchallange.ui.theme.FiapChallangeTheme
import com.androidestudos.fiapchallange.ui.view.CreateTarefasScreen
import com.androidestudos.fiapchallange.ui.view.DeleteTarefaScreen
import com.androidestudos.fiapchallange.ui.view.TarefasContainer
import com.androidestudos.fiapchallange.ui.view.TarefasScreen
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FiapChallangeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    LazyColumn(modifier = Modifier.padding(innerPadding)){
                        item {
                            TarefasContainer()
                        }
                    }
                }
            }
        }
    }
}