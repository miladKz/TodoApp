package kazemi.milad.android.todoapp.ui.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kazemi.milad.android.todoapp.R
import kazemi.milad.android.todoapp.ui.setting.SettingViewModel
import kazemi.milad.android.todoapp.utils.UiEvent
import kotlinx.coroutines.launch

@Composable
fun TodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel(),
    settingViewModel: SettingViewModel
) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val snackBarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    val result = snackBarHostState.showSnackbar(
                        message = event.message, actionLabel = event.action
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TodoListEvent.OnUndoDeleteClick)
                    }
                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    TodoListScreenDrawer(
        drawerState = drawerState,
        settingsViewModel = settingViewModel
    ) {
        Scaffold(
            topBar = {
                TodoListAppBar(onMenuClick = {
                    scope.launch { drawerState.open() }
                })
            },
            snackbarHost = { SnackbarHost(snackBarHostState) }, floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.onEvent(TodoListEvent.OnAddTodoClick)
                }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_new_todo)
                    )
                }
            }) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(todos.value) { todo ->
                    TodoItem(
                        todo = todo,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .clickable {
                                viewModel.onEvent(TodoListEvent.OnTodoClick(todo))
                            }
                    )

                }
            }
        }
    }
}