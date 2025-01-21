package kazemi.milad.android.todoapp.data.settings

interface SettingRepository {
    suspend fun saveLanguageCode(code: String)

    suspend fun getLanguageCode(): String

    suspend fun saveThemeMode(isDarkMode: Boolean)

    fun isDarkMode(): Boolean
}