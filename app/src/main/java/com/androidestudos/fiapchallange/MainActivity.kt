package com.androidestudos.fiapchallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.androidestudos.fiapchallange.ui.NavGraph
import com.androidestudos.fiapchallange.ui.theme.FiapChallangeTheme
import com.androidestudos.fiapchallange.ui.view.pages.tarefas.TarefasContainer
import com.androidestudos.fiapchallange.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val topBarState = rememberSaveable() {
                mutableStateOf(false)
            }

            // Control Topbar Back Button visibility
            when (navBackStackEntry?.destination?.route) {
                Constants.Ui.TAREFAS_ROUTE_ID -> {
                    // Hide TopBar
                    topBarState.value = false
                }
                Constants.Ui.EMPLOYEES_ROUTE_ID -> {
                    // Show TopBar
                    topBarState.value = true
                }
            }

            val topBarTitle = remember {
                mutableStateOf("")
            }
            FiapChallangeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    SanyuTopBar(navHostController = navHostController, topBarTitle, topBarState)
                }) { innerPadding ->
                    NavGraph(topBarTitle, innerPadding, navHostController)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SanyuTopBar(
        navHostController: NavHostController,
        title: MutableState<String>,
        topBarState: MutableState<Boolean>
    ) {
        TopAppBar(

            title = { Text(title.value) },
            navigationIcon = {
                if (topBarState.value == true) {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            },
        )
    }
}