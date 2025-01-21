package kazemi.milad.android.todoapp.ui.todo_list

import BrightnessHigh
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kazemi.milad.android.todoapp.R
import kazemi.milad.android.todoapp.ui.setting.Language
import kazemi.milad.android.todoapp.ui.setting.SettingEvent
import kazemi.milad.android.todoapp.ui.setting.SettingViewModel


@Composable
fun TodoListScreenDrawer(
    drawerState: DrawerState,
    settingsViewModel: SettingViewModel,
    content: @Composable () -> Unit
) {
    val isDarkTheme = settingsViewModel.isDarkMode.collectAsState()
    val currentLanguage = settingsViewModel.language.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                settingsViewModel = settingsViewModel,
                isDarkTheme = isDarkTheme.value,
                currentLanguage = currentLanguage.value,
                onLanguageChange = { newLanguage ->
                    settingsViewModel.onEvent(SettingEvent.OnLanguageChanged(newLanguage))
                },
                onThemeChange = { isDark ->
                    settingsViewModel.onEvent(
                        SettingEvent.OnThemeChanged(
                            isDark
                        )
                    )
                }
            )
        }
    ) {
        content()
    }
}

@Composable
fun DrawerContent(
    isDarkTheme: Boolean,
    currentLanguage: Language,
    onLanguageChange: (Language) -> Unit,
    onThemeChange: (Boolean) -> Unit,
    settingsViewModel: SettingViewModel,
) {
    ModalDrawerSheet {
        Text(
            text = stringResource(R.string.settings),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge
        )

        ThemeSwitch(isDarkTheme = isDarkTheme, onThemeChange = onThemeChange)
        LanguageSelector(
            currentLanguage = currentLanguage, onLanguageChange = onLanguageChange,
            availableLanguages = settingsViewModel.availableLanguages
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelector(
    currentLanguage: Language,
    onLanguageChange: (Language) -> Unit,
    availableLanguages: List<Language>
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .background(Color.White)
    ) {
        TextField(
            value = currentLanguage.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.select_language)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = true }
        ) {
            availableLanguages.forEach { language ->
                DropdownMenuItem(
                    text = { Text(language.name) },
                    onClick = {
                        onLanguageChange(language)
                        expanded = true
                    }
                )
            }
        }
    }
}

@Composable
fun ThemeSwitch(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberVectorPainter(BrightnessHigh),
                contentDescription = "Brightness Icon",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Text(stringResource(R.string.theme_mode))
        }
        Switch(
            checked = isDarkTheme,
            onCheckedChange = onThemeChange
        )
    }
}
