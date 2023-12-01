package youngdevs.production.firstpractice

data class WeatherScreenState(
    val weatherInfo: WeatherInfo? = null,
    val hourlyForecastList: List<HourlyForecast> = emptyList(),
    val dailyForecastList: List<DailyForecast> = emptyList(),
    val additionalWeatherInfo: AdditionalWeatherInfo? = null
)
