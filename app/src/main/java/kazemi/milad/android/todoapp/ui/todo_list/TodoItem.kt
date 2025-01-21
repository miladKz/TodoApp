package kazemi.milad.android.todoapp.ui.todo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kazemi.milad.android.todoapp.R
import kazemi.milad.android.todoapp.data.todo.Todo

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .shadow(
                elevation = 4.dp, // ارتفاع سایه
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TodoDetails(
            todo = todo, modifier = Modifier
                .weight(1f)
        )
        TodoCheckbox(todo = todo, onEvent = onEvent)
        TodoDeleteButton(todo = todo, onEvent = onEvent)
    }
}

@Composable
fun TodoDetails(todo: Todo, modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TodoTitle(todo = todo)
        todo.description?.let {
            TodoDescription(description = it)
        }
    }
}

@Composable
fun TodoTitle(todo: Todo) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = todo.title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun TodoDescription(description: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = description)
}

@Composable
fun TodoCheckbox(todo: Todo, onEvent: (TodoListEvent) -> Unit) {
    Checkbox(
        checked = todo.isDone,
        onCheckedChange = { isChecked ->
            onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
        }
    )
}

@Composable
fun TodoDeleteButton(todo: Todo, onEvent: (TodoListEvent) -> Unit) {
    IconButton(onClick = {
        onEvent(TodoListEvent.OnDeleteTodoClick(todo))
    }) {
        Icon(Icons.Outlined.Delete, contentDescription = stringResource(R.string.delete))
    }
}
