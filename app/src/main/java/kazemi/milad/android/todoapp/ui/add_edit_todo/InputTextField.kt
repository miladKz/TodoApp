package kazemi.milad.android.todoapp.ui.add_edit_todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
    height: Dp = 85.dp
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder)
        },
        modifier = modifier
            .then(defaultModifier(height)),
        singleLine = maxLines <= 1,
        maxLines = maxLines,
        colors = textFieldColors(),
        shape = RoundedCornerShape(8.dp)
    )
}

private fun defaultModifier(height: Dp): Modifier = Modifier
    .fillMaxWidth()
    .height(height)
    .padding(vertical = 6.dp, horizontal = 16.dp)
    .background(
        color = Color.White,
        shape = RoundedCornerShape(8.dp)
    )
    .border(
        width = 1.dp,
        color = Color.Gray,
        shape = RoundedCornerShape(8.dp)
    )

@Composable
private fun textFieldColors() = TextFieldDefaults.colors(
    unfocusedContainerColor = MaterialTheme.colorScheme.background,
    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
)

