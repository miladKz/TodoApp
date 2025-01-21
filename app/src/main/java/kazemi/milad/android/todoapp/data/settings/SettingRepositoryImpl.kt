package kazemi.milad.android.todoapp.data.settings

class SettingRepositoryImpl(
    private val settingPreferences: SettingPreferences
) : SettingRepository {
    override suspend fun saveLanguageCode(code: String) {
        settingPreferences.saveLanguageCode(code)
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