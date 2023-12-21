package youngdevs.production.firstpractice.datasource

import youngdevs.production.firstpractice.data.WeatherData

class WeatherLocalDataSource(private val database: WeatherDatabase) {
    class WeatherDatabase {

    }

    fun getSavedWeather(): WeatherData {
        // Получение сохраненных данных о погоде из локальной БД
        return database.weatherDao().getWeather()
    }

    fun saveWeatherData(weatherData: WeatherData) {
        // Сохранение погодных данных в локальной БД
        database.weatherDao().insertWeather(weatherData)
    }
}
