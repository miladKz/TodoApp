package kazemi.milad.android.todoapp.ui.todo_list

import kazemi.milad.android.todoapp.data.todo.Todo

sealed class TodoListEvent {
    data class OnDeleteTodoClick(val todo: Todo) : TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodoListEvent()
    data object OnUndoDeleteClick : TodoListEvent()
    data class OnTodoClick(val todo: Todo) : TodoListEvent()
    data object OnAddTodoClick : TodoListEvent()
}