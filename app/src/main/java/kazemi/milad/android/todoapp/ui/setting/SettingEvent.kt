package kazemi.milad.android.todoapp.ui.setting

sealed class SettingEvent {
    data class OnLanguageChanged(val language: Language) : SettingEvent()
    data class OnThemeChanged(val isDarkMode: Boolean) : SettingEvent()
}