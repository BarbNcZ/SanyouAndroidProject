package com.androidestudos.fiapchallange

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.androidestudos.fiapchallange.ui.NavGraph
import com.androidestudos.fiapchallange.ui.Route
import com.androidestudos.fiapchallange.ui.theme.FiapChallangeTheme
import com.androidestudos.fiapchallange.ui.view.atoms.SanyuTopBar

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private var route: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()

            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val topBarState = rememberSaveable {
                mutableStateOf(false)
            }

            val menuVisible = rememberSaveable { mutableStateOf(false) }

            val currentRoute = Route.entries.find {
                it.routeId == navBackStackEntry?.destination?.route?.split("/")?.get(0)
            }
            topBarState.value = currentRoute?.showBackButton == true
            menuVisible.value = currentRoute?.showMenu == true

            val topBarTitle = remember {
                mutableStateOf("")
            }


            val isManager = remember {
                mutableStateOf(false)
            }

            FiapChallangeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    SanyuTopBar(
                        navHostController =
                            if (::navHostController.isInitialized) {
                                navHostController
                            } else null,
                        title = topBarTitle,
                        topBarState = topBarState,
                        menuVisible = menuVisible,
                        isLoginPage = currentRoute == Route.LOGIN,
                        isManager = isManager.value
                    )
                }) { innerPadding ->
                    NavGraph(topBarTitle, innerPadding, navHostController, isManager = isManager)
                }
            }
            invalidateMenu()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (::navHostController.isInitialized) {
            when(item.itemId){

                R.id.chart_samples -> {

                    navHostController.navigate(Route.CHARTS_SAMPLES.routeId)

                    return true

                }

                R.id.ranking -> {

                    navHostController.navigate(Route.RANKING.routeId)

                    return true

                }

                R.id.logout -> {

                    navHostController.navigate(Route.LOGIN.routeId)

                    return true

                }
            }
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val currentRoute = Route.entries.find {
            it.routeId == route
        }

        this@MainActivity.actionBar?.setDisplayHomeAsUpEnabled(
            currentRoute?.showBackButton == true
        )
        this@MainActivity.actionBar?.setDisplayShowHomeEnabled(
            currentRoute?.showBackButton == true
        )
        menu?.setGroupVisible(R.id.main_menu, currentRoute?.showMenu == true)

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        this.menuInflater.inflate(
            R.menu.main_menu,
            menu
        )

        return true

    }
}