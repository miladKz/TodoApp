package kazemi.milad.android.todoapp.ui.add_edit_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kazemi.milad.android.todoapp.R
import kazemi.milad.android.todoapp.utils.UiEvent

@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    HandleUiEvents(viewModel, snackBarHostState, onPopBackStack)

    Scaffold(
        topBar = { TodoTopBar() },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            SaveTodoButton { viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick) }
        }
    ) { paddingValues ->
        TodoContent(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun HandleUiEvents(
    viewModel: AddEditTodoViewModel,
    snackBarHostState: SnackbarHostState,
    onPopBackStack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action,
                        duration = SnackbarDuration.Short
                    )
                }

                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TodoTopBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.add_new_todo)) }
    )
}

@Composable
private fun SaveTodoButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            Icons.Default.Check,
            contentDescription = stringResource(R.string.save_todo)
        )
    }
}

@Composable
private fun TodoContent(viewModel: AddEditTodoViewModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(4.dp))

        // Title TextField
        InputTextField(
            value = viewModel.title,
            onValueChange = {
                viewModel.onEvent(AddEditTodoEvent.OnTitleChanged(it))
            },
            placeholder = stringResource(R.string.title)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description TextField
        InputTextField(
            value = viewModel.description,
            onValueChange = {
                viewModel.onEvent(AddEditTodoEvent.OnDescriptionChanged(it))
            },
            placeholder = stringResource(R.string.description),
            height = 140.dp,
            maxLines = 5
        )
    }
}

