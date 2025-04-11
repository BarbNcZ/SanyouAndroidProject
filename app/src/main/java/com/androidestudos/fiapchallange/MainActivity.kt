package com.androidestudos.fiapchallange

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.androidestudos.fiapchallange.ui.NavGraph
import com.androidestudos.fiapchallange.ui.Route
import com.androidestudos.fiapchallange.ui.theme.FiapChallangeTheme
import com.androidestudos.fiapchallange.utils.Constants
import com.androidestudos.fiapchallange.utils.Constants.Ui.CREATE_TASK_ROUTE_ID
import com.androidestudos.fiapchallange.utils.Constants.Ui.DELETE_TASK_ROUTE_ID
import com.androidestudos.fiapchallange.utils.Constants.Ui.EMPLOYEES_ROUTE_ID
import com.androidestudos.fiapchallange.utils.Constants.Ui.LOGIN_ROUTE_ID
import com.androidestudos.fiapchallange.utils.Constants.Ui.MENU_ROUTE_ID
import com.androidestudos.fiapchallange.utils.Constants.Ui.RANKING_ROUTE_ID
import com.androidestudos.fiapchallange.utils.Constants.Ui.TASKS_ROUTE_ID

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private var route: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()

            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val topBarState = rememberSaveable() {
                mutableStateOf(false)
            }

            val menuVisible = rememberSaveable() { mutableStateOf(false) }


            // Control Topbar Back Button visibility
            when (navBackStackEntry?.destination?.route?.split("/")?.get(0)) {


                Constants.Ui.TASKS_ROUTE_ID -> {
                    // Hide TopBar
                    topBarState.value = false
                    menuVisible.value = true

                }

                Constants.Ui.CREATE_TASK_ROUTE_ID -> {
                    // Hide TopBar
                    topBarState.value = true
                    menuVisible.value = true
                }

                Constants.Ui.DELETE_TASK_ROUTE_ID -> {
                    // Hide TopBar
                    topBarState.value = true
                    menuVisible.value = true
                }

                Constants.Ui.MENU_ROUTE_ID -> {
                    // Hide TopBar
                    topBarState.value = false
                    menuVisible.value = true
                }

                Constants.Ui.EMPLOYEES_ROUTE_ID -> {
                    // Show TopBar
                    topBarState.value = true
                    menuVisible.value = true
                }

                Constants.Ui.LOGIN_ROUTE_ID -> {
                    // Hide TopBar
                    topBarState.value = false
                    route = Constants.Ui.LOGIN_ROUTE_ID

                    menuVisible.value = false

                }
            }

            val topBarTitle = remember {
                mutableStateOf("")
            }



            FiapChallangeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    SanyuTopBar(navHostController = navHostController, topBarTitle, topBarState, menuVisible)
                }) { innerPadding ->
                    NavGraph(topBarTitle, innerPadding, navHostController)
                }
            }

            invalidateMenu()

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.logout -> if (
                ::navHostController.isInitialized
            ){

                navHostController.navigate(Route.Login.route)

                return true

            }
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.setGroupVisible(R.id.main_menu, route != Constants.Ui.LOGIN_ROUTE_ID)

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        this.menuInflater.inflate(
            R.menu.main_menu,
            menu
        )

        return true

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SanyuTopBar(
        navHostController: NavHostController,
        title: MutableState<String>,
        topBarState: MutableState<Boolean>,
        menuVisible: MutableState<Boolean>
    ) {

        var expanded by remember { mutableStateOf(false) }

        TopAppBar(

            actions = {

                if (menuVisible.value){

                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = {
                                expanded = false
                                if (::navHostController.isInitialized){

                                    navHostController.navigate(Route.Login.route)

                                }
                            }
                        )
                    }
                }
            },

            title = { Text(title.value) },
            navigationIcon = {
                if (topBarState.value == true) {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            },
        )
    }
}