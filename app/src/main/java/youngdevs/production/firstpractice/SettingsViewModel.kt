package youngdevs.production.firstpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    private val _settingsState = MutableLiveData<SettingsScreenState>()
    val settingsState: LiveData<SettingsScreenState> = _settingsState

    init {
        loadSettings()
    }

    fun loadSettings() {
        _settingsState.value = SettingsScreenState(cityName = "Москва", useCurrentLocation = false)
    }

    fun saveSettings(cityName: String, useCurrentLocation: Boolean) {
        _settingsState.value = SettingsScreenState(cityName = cityName, useCurrentLocation = useCurrentLocation)
    }
}