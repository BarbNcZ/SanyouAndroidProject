package com.androidestudos.fiapchallange.ui.view.atoms

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.androidestudos.fiapchallange.ui.Route
import com.androidestudos.fiapchallange.BuildConfig
import com.androidestudos.fiapchallange.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SanyuTopBar(
    navHostController: NavHostController?,
    title: MutableState<String>,
    topBarState: MutableState<Boolean>,
    menuVisible: MutableState<Boolean>,
    isLoginPage: Boolean = false,
    isManager: Boolean,
) {

    var expanded by remember { mutableStateOf(false) }

    TopAppBar(

        actions = {

            if (menuVisible.value){

                var currentRoute: Route? = null
                navHostController?.let {
                    val navBackStackEntry by it.currentBackStackEntryAsState()
                    currentRoute = Route.entries.find {
                        it.routeId == navBackStackEntry?.destination?.route?.split("/")?.get(0)
                    }
                }

                if (!isLoginPage) {
                    if (currentRoute != Route.RANKING) {
                        IconButton(onClick = { navHostController?.navigate(Route.RANKING.routeId) }) {
                            Icon(Icons.Default.Star, contentDescription = "Ranking")
                        }
                    }

                    if (currentRoute != Route.CHARTS && isManager) {
                        IconButton(onClick = { navHostController?.navigate(Route.CHARTS.routeId) }) {
                            Icon(painterResource(R.drawable.ic_pie_chart), contentDescription = "Graficos")
                        }
                    }

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
                                navHostController?.navigate(Route.LOGIN.routeId)
                            }
                        )
                    }
                }
            }
        },

        title = { Text(title.value) },
        navigationIcon = {
            if (topBarState.value == true) {
                IconButton(onClick = { navHostController?.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
    )
}