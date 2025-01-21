package kazemi.milad.android.todoapp.ui.todo_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kazemi.milad.android.todoapp.R

import kazemi.milad.android.todoapp.data.todo.Todo
import kazemi.milad.android.todoapp.data.todo.TodoRepository
import kazemi.milad.android.todoapp.utils.Routes
import kazemi.milad.android.todoapp.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val todos = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnDeleteTodoClick -> handleDeleteTodoClick(event.todo)
            is TodoListEvent.OnUndoDeleteClick -> handleUndoDeleteClick()
            is TodoListEvent.OnDoneChange -> handleDoneChange(event.todo, event.isDone)
            is TodoListEvent.OnTodoClick -> navigateToEditTodo(event.todo.id)
            is TodoListEvent.OnAddTodoClick -> navigateToAddTodo()
        }
    }

    private fun handleDeleteTodoClick(todo: Todo) {
        viewModelScope.launch {
            deletedTodo = todo
            repository.deleteTodo(todo)
            sendUiEvent(
                UiEvent.ShowSnackBar(
                    message = context.getString(R.string.deleted_todo),
                    action = context.getString(R.string.undo)
                )
            )
        }
    }

    private fun handleUndoDeleteClick() {
        deletedTodo?.let { todo ->
            viewModelScope.launch {
                repository.insertTodo(todo)
            }
        }
    }

    private fun handleDoneChange(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            repository.insertTodo(
                todo.copy(isDone = isDone)
            )
        }
    }

    private fun navigateToEditTodo(todoId: Int?) {
        sendUiEvent(UiEvent.Navigate("${Routes.ADD_EDIT_TODO}?todoId=$todoId"))
    }

    private fun navigateToAddTodo() {
        sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
