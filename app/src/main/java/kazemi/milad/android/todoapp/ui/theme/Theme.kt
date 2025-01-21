package kazemi.milad.android.todoapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40 ,
    background = Color(0xFF1C022A),
    surface = Color(0xFF5C4881),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF19096B),
    onTertiary = Color(0xFF4F14FF),
    onBackground = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF43054D),
    onSurface = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF43054D),
)


private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40 ,
    background = Color(0xC8ECE2FF),
    surface = Color(0xFFB48CFF),
    onPrimary = Color(0xFFBBFF7F),
    onSecondary = Color(0xFF070017),
    onTertiary = Color(0xFFBBFF7F),
    onBackground = Color(0xFF000000),
    surfaceVariant = Color(0xC8EBFFE2),
    onSurface = Color(0xFF000000),
    primaryContainer = Color(0xFFFFFFFF),
)

@Composable
fun TodoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}