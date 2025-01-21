package kazemi.milad.android.todoapp.ui.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kazemi.milad.android.todoapp.data.settings.SettingRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    //private val settingRepository: SettingRepository
) : ViewModel() {

    private val _uiEvent = Channel<SettingEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _language = MutableStateFlow("")
    val language: StateFlow<String> get() = _language

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> get() = _isDarkMode

    init {
        loadSetting()
    }

    private fun loadSetting() {
        viewModelScope.launch {
        //    _language = settingRepository.getLanguageCode()
        //    _isDarkMode = settingRepository.isDarkMode()
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
         //   settingRepository.saveLanguage(language)
            _language.value = language
        }

    }

    private fun updateTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
       //     settingRepository.saveThemeMode(isDarkMode)
            _isDarkMode.value = isDarkMode
        }
    }
}