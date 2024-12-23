package kazemi.milad.android.todoapp.data


data class Todo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    val id: Int? = null
)