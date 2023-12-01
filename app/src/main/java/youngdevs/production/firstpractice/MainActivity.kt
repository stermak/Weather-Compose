package youngdevs.production.firstpractice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import youngdevs.production.firstpractice.ui.theme.FirstPracticeTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstPracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    MainScreen(navController = navController)
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = if (currentRoute == "Weather") "Weather App" else "Settings") },
                    navigationIcon = {
                        IconButton(onClick = {
                            if (currentRoute == "Weather") {
                                navController.navigate("Settings")
                            } else {
                                navController.popBackStack()
                            }
                        }) {
                            val icon =
                                if (currentRoute == "Weather") Icons.Default.Settings else Icons.Default.ArrowBack
                            val contentDescription =
                                if (currentRoute == "Weather") "Settings" else "Back"
                            Icon(imageVector = icon, contentDescription = contentDescription)
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar {
                    IconButton(onClick = { navController.navigate("Weather") }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Weather")
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { navController.navigate("Settings") }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            },
            content = {
                NavHost(navController = navController, startDestination = "Weather") {
                    composable("Weather") { WeatherScreen() }
                    composable("Settings") {
                        SettingsScreen(settingsViewModel = viewModel(), navController = navController)
                    }
                }
            }
        )
    }