package youngdevs.production.firstpractice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import youngdevs.production.firstpractice.ui.theme.kaif

data class WeatherInfo(val temperature: String, val weatherType: String)
data class HourlyForecast(val hour: String, val temperature: String)
data class DailyForecast(val day: String, val temperatureMax: String, val temperatureMin: String)

data class AdditionalWeatherInfo(
    val humidity: String,
    val visibility: String,
    val uvIndex: String,
    val windSpeed: String,
    val pressure: String,
    val feelsLike: String
)

@Composable
fun AdditionalWeatherCard(info: AdditionalWeatherInfo) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = "Humidity: ${info.humidity}%", style = MaterialTheme.typography.bodySmall, color = kaif)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Wind Speed: ${info.windSpeed} m/s", style = MaterialTheme.typography.bodySmall, color = kaif)
    }
}

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val state = weatherViewModel.weatherState.observeAsState().value ?: return

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        state.weatherInfo?.let { weatherInfo ->
            item { WeatherCard(weatherInfo) }
        }
        state.additionalWeatherInfo?.let { additionalInfo ->
            item { AdditionalWeatherCard(additionalInfo) }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { Text(text = "Hourly Forecast", style = MaterialTheme.typography.displayLarge, color = kaif) }
        items(state.hourlyForecastList) { forecast ->
            HourlyForecastCard(forecast)
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { Text(text = "7 Days Forecast", style = MaterialTheme.typography.displayLarge, color = kaif) }
        items(state.dailyForecastList) { forecast ->
            DailyForecastCard(forecast)
        }
    }
}


@Composable
fun WeatherCard(weatherInfo: WeatherInfo) {
    Box(modifier = Modifier.padding(16.dp)) {
        Column {
            Text(text = "${weatherInfo.temperature}°", style = MaterialTheme.typography.displayLarge, color = kaif)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = weatherInfo.weatherType, style = MaterialTheme.typography.bodyMedium, color = kaif)
        }
    }
}

@Composable
fun HourlyForecastCard(hourlyForecast: HourlyForecast) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = hourlyForecast.hour, style = MaterialTheme.typography.bodySmall, color = kaif)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${hourlyForecast.temperature}°", style = MaterialTheme.typography.bodySmall, color = kaif)
    }
}

@Composable
fun DailyForecastCard(dailyForecast: DailyForecast) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = dailyForecast.day, style = MaterialTheme.typography.bodySmall, color = kaif)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${dailyForecast.temperatureMax}° / ${dailyForecast.temperatureMin}°", style = MaterialTheme.typography.bodySmall, color = kaif)
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}