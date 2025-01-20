package kazemi.milad.android.todoapp.ui.todo_list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kazemi.milad.android.todoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListAppBar(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        }
    )
}
