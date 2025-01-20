package kazemi.milad.android.todoapp.ui.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kazemi.milad.android.todoapp.R
import kazemi.milad.android.todoapp.ui.setting.SettingEvent
import kazemi.milad.android.todoapp.ui.setting.SettingViewModel


@OptIn(ExperimentalMaterial3Api::class)

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
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.settings),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                // Language Spinner
                var expanded by remember { mutableStateOf(false) }
                val languages = listOf("English", "فارسی")

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = currentLanguage.value,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.select_language)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        languages.forEach { language ->
                            DropdownMenuItem(
                                text = { Text(language) },
                                onClick = {
                                    settingsViewModel.onEvent(
                                        SettingEvent.OnLanguageChanged(
                                            language
                                        )
                                    )
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                // Theme Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.theme_mode))
                    Switch(
                        checked = isDarkTheme.value,
                        onCheckedChange = {
                            settingsViewModel.onEvent(SettingEvent.OnThemeChanged(it))
                        }
                    )
                }
            }
        }
    ) {
        content()
    }
}

