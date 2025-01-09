package kazemi.milad.android.todoapp.ui.add_edit_todo

sealed class AddEditTodoEvent {
    data class OnTitleChanged(val title: String) : AddEditTodoEvent()
    data class OnDescriptionChanged(val description: String) : AddEditTodoEvent()
    data object OnSaveTodoClick : AddEditTodoEvent()
}