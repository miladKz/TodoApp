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
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF4F053C),
    surface = Color(0xFFE00000),
    onPrimary = Color(0xFF5120E1),
    onSecondary = Color(0xFF070017),
    onTertiary = Color(0xFF4F14FF),
    onBackground = Color(0xFF39D701),
    onSurface = Color(0xFF00D0F5),

)


private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40 ,
    background = Color(0xC8ECE2FF),
    surface = Color(0xFFB48CFF),
    onPrimary = Color(0xFF5120E1),
    onSecondary = Color(0xFF070017),
    onTertiary = Color(0xFF4F14FF),
    onBackground = Color(0xFF000000),
    surfaceVariant = Color(0xC8EBFFE2),
    onSurface = Color(0xFF000000),
)

@Composable
fun TodoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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