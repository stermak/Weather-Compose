package youngdevs.production.firstpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import youngdevs.production.firstpractice.data.WeatherData
import youngdevs.production.firstpractice.datasource.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _weatherState = MutableLiveData<WeatherScreenState>()
    val weatherState: LiveData<WeatherScreenState> = _weatherState
    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> = _weatherData

    init {
        loadWeatherData()
    }

    fun loadWeather(cityName: String) {
        viewModelScope.launch {
            val data = weatherRepository.getWeather(cityName)
            _weatherData.value = data
        }
    }

    fun loadWeatherData() {
        val weatherInfo = WeatherInfo("22", "Sunny")
        val additionalInfo = AdditionalWeatherInfo("60", "5km", "3", "5", "1012", "20")
        val hourlyForecastList = listOf(
            HourlyForecast("10:00", "20"),
            HourlyForecast("11:00", "21")
        )
        val dailyForecastList = listOf(
            DailyForecast("Monday", "24", "18"),
            DailyForecast("Tuesday", "23", "17"),
            DailyForecast("Wednesday", "22", "16"),
            DailyForecast("Thursday", "21", "15"),
            DailyForecast("Friday", "20", "14"),
            DailyForecast("Saturday", "19", "13"),
            DailyForecast("Sunday", "18", "12")

        )

        _weatherState.value = WeatherScreenState(
            weatherInfo = weatherInfo,
            hourlyForecastList = hourlyForecastList,
            dailyForecastList = dailyForecastList,
            additionalWeatherInfo = additionalInfo
        )
    }
}
