package kazemi.milad.android.todoapp.ui.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kazemi.milad.android.todoapp.data.settings.SettingRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {
    private var _language by mutableStateOf("")
        private set


    private var _isDarkMode by mutableStateOf(false)

    init {
        loadSetting()
    }

    private fun loadSetting() {
        viewModelScope.launch {
            _language = settingRepository.getLanguageCode()
            _isDarkMode = settingRepository.isDarkMode()
        }
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.OnLanguageChanged -> updateLanguage(event.language)
            is SettingEvent.OnThemeChanged -> updateTheme(event.isDarkMode)
        }
    }

    private fun updateLanguage(language: String) {
        viewModelScope.launch {
            settingRepository.saveLanguage(language)
            _language = language
        }

    }

    private fun updateTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingRepository.saveThemeMode(isDarkMode)
            _isDarkMode = isDarkMode
        }
    }
}