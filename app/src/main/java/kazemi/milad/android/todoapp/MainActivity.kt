package kazemi.milad.android.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kazemi.milad.android.todoapp.ui.add_edit_todo.AddEditTodoScreen
import kazemi.milad.android.todoapp.ui.setting.SettingViewModel
import kazemi.milad.android.todoapp.ui.theme.TodoAppTheme
import kazemi.milad.android.todoapp.ui.todo_list.TodoListScreen
import kazemi.milad.android.todoapp.utils.Routes
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingViewModel: SettingViewModel = hiltViewModel()
            val isDarkMode = settingViewModel.isDarkMode.collectAsState().value
            val currentLanguage = settingViewModel.language.collectAsState().value
            var composableKey by remember { mutableIntStateOf(0) }

            LaunchedEffect(currentLanguage) {
                val locale = Locale(currentLanguage.code)

                updateLocale(locale)

                composableKey++

            }
            key(composableKey) {
                TodoAppTheme(
                    darkTheme = isDarkMode
                ) {
                    println(settingViewModel.isDarkMode.collectAsState().value)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.TODO_LIST
                    ) {
                        composable(Routes.TODO_LIST) {
                            TodoListScreen(
                                settingViewModel = settingViewModel,
                                onNavigate = {
                                    navController.navigate(it.route)
                                }
                            )
                        }

                        composable(Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(name = "todoId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditTodoScreen(
                                onPopBackStack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateLocale(locale: Locale) {
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        val context = createConfigurationContext(config)
        resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}

