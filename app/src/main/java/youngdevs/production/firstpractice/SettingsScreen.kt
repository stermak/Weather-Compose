package youngdevs.production.firstpractice

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import youngdevs.production.firstpractice.ui.theme.kaif

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = viewModel(), navController: NavController) {
    val state = settingsViewModel.settingsState.observeAsState().value ?: return

    Card(
        modifier = Modifier.fillMaxSize().background(kaif)
    ) {
        Scaffold(
            modifier = Modifier.zIndex(1f),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Настройки", color = kaif) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Назад"
                            )
                        }
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    TextField(
                        value = state.cityName,
                        onValueChange = { settingsViewModel.saveSettings(it, state.useCurrentLocation) },
                        label = { Text("Город") },
                        enabled = !state.useCurrentLocation
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = state.useCurrentLocation,
                            onCheckedChange = { settingsViewModel.saveSettings(state.cityName, it) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Использовать текущее местоположение", color = kaif)
                    }
                }
            }
        )
    }
}