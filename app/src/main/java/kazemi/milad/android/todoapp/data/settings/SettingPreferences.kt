package kazemi.milad.android.todoapp.data.settings

import android.content.Context
import android.content.SharedPreferences

class SettingPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "setting_preferences", Context.MODE_PRIVATE
    )

    companion object {
        const val KEY_LANGUAGE = "key_language"
        const val KEY_THEME = "key_theme"
    }

    fun saveLanguageCode(code: String) {
        sharedPreferences.edit().putString(KEY_LANGUAGE, code).apply()
    }

    fun getLanguage(): String {
        return sharedPreferences.getString(KEY_LANGUAGE, "en").toString()
    }

    fun saveTheme(isDarkMode: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_THEME, isDarkMode).apply()
    }

    fun getTheme(): Boolean {
        return sharedPreferences.getBoolean(KEY_THEME, false)
    }
}