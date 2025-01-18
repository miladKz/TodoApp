package kazemi.milad.android.todoapp.data.settings

class SettingRepositoryImpl(
    private val settingPreferences: SettingPreferences
) : SettingRepository {
    override suspend fun saveLanguage(languageCode: String) {
        settingPreferences.saveLanguage(languageCode)
    }

    override suspend fun getLanguageCode(): String {
        return settingPreferences.getLanguage()
    }

    override suspend fun saveThemeMode(isDarkMode: Boolean) {
        settingPreferences.saveTheme(isDarkMode)
    }

    override fun isDarkMode(): Boolean {
        return settingPreferences.getTheme()
    }
}