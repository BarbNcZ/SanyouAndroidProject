package com.androidestudos.fiapchallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidestudos.fiapchallange.data.CreateTarefaResult
import com.androidestudos.fiapchallange.ui.theme.FiapChallangeTheme
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FiapChallangeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<TarefasViewModel>()
                    val state = viewModel.state.collectAsStateWithLifecycle()
                    Tarefas(innerPadding, state.value, viewModel::createTarefa)
                }
            }
        }
    }
}

@Composable
fun Tarefas(paddingValues: PaddingValues, state: CreateTarefaResult?, criarTarefa: (String) -> Unit) {
    val codigoTarefa = -1

    var text by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            text = "Resultado ${state?.idTarefa ?: "Nenhum"}"
        )

        TextField(text, {text = it})

        Button({
            criarTarefa(text)
        }
        ) {
            Text("Criar Tarefa")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FiapChallangeTheme {
    }
}