package kazemi.milad.android.todoapp.ui.theme

import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

object AppThemeColors {
    val DarkColorScheme = darkColorScheme(
        primary = PurpleVariant,
        secondary = DarkGray,
        tertiary = Wine,
        background = NearBlack,
        surface = DarkPurple,
        onPrimary = White,
        onSecondary = DeepPurple,
        onTertiary = DeepPurple2,
        onBackground = White,
        surfaceVariant = RoyalPurple,
        onSurface = White,
        primaryContainer = RoyalPurple
    )

    val LightColorScheme = lightColorScheme(
        primary = Purple,
        secondary = DarkGray,
        tertiary = Wine,
        background = LightPurpleBackground,
        surface = LightPurple,
        onPrimary = LightGreen,
        onSecondary = Black,
        onTertiary = LightGreen,
        onBackground = Black,
        surfaceVariant = LightGreenBackground,
        onSurface = Black,
        primaryContainer = White
    )
}

@Composable
fun TodoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = getColorScheme(context, darkTheme, dynamicColor)
    val layoutDirection = getLayoutDirection(context)

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

private fun getColorScheme(
    context: Context,
    darkTheme: Boolean,
    dynamicColor: Boolean
): ColorScheme {
    return when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        darkTheme -> AppThemeColors.DarkColorScheme
        else -> AppThemeColors.LightColorScheme
    }
}

private fun getLayoutDirection(context: Context): LayoutDirection {
    val currentLocale = context.resources.configuration.locales[0]
    val isRtl = currentLocale.language.lowercase() == "fa"
    return if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
}