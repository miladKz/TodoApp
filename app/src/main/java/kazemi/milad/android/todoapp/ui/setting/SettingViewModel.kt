package kazemi.milad.android.todoapp.ui.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kazemi.milad.android.todoapp.data.settings.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {
    var availableLanguages: List<Language> = listOf(
        Language("english", "en"),
        Language("فارسی", "fa")
    )
    private var _language = MutableStateFlow(Language(name = "english", code = "en"))
    val language: StateFlow<Language> get() = _language

    private var _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> get() = _isDarkMode


    init {
        loadSetting()
    }

    private fun loadSetting() {
        viewModelScope.launch {
            val languageCode: String = settingRepository.getLanguageCode();
            _language.value = getLanguageByCode(languageCode)
            _isDarkMode.value = settingRepository.isDarkMode()
        }
    }


    fun getLanguageByCode(languageCode: String): Language {
        return availableLanguages.find { it.code == languageCode }
            ?: Language(name = "english", code = "en")
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.OnLanguageChanged -> updateLanguage(event.language)
            is SettingEvent.OnThemeChanged -> updateTheme(event.isDarkMode)
        }
    }

    private fun updateLanguage(language: Language) {
        viewModelScope.launch {
            settingRepository.saveLanguageCode(language.code)
            _language.value = language
        }

    }

    private fun updateTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingRepository.saveThemeMode(isDarkMode)
            _isDarkMode.value = isDarkMode
        }
    }
}