package youngdevs.production.firstpractice.datasource

import youngdevs.production.firstpractice.data.WeatherData

class WeatherRemoteDataSource(private val apiService: WeatherApiService) {
    suspend fun getWeather(cityName: String): WeatherData {
        // Здесь вызывается API и возвращаются данные
        return apiService.getWeather(cityName)
    }
}