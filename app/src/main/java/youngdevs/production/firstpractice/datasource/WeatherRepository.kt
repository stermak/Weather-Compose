package youngdevs.production.firstpractice.datasource

import youngdevs.production.firstpractice.data.WeatherData

class WeatherRepository(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) {
    suspend fun getWeather(cityName: String): WeatherData {
        return try {
            // Пытаемся получить данные с сервера
            val weather = remoteDataSource.getWeather(cityName)
            localDataSource.saveWeatherData(weather) // Сохраняем данные локально
            weather
        } catch (e: Exception) {
            // В случае ошибки возвращаем локально кешированные данные
            localDataSource.getSavedWeather()
        }
    }
}
