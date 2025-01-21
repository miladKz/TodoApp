package kazemi.milad.android.todoapp.ui.add_edit_todo

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kazemi.milad.android.todoapp.R
import kazemi.milad.android.todoapp.data.todo.Todo
import kazemi.milad.android.todoapp.data.todo.TodoRepository
import kazemi.milad.android.todoapp.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadTodoFromState(savedStateHandle)
    }

    private fun loadTodoFromState(savedStateHandle: SavedStateHandle) {
        val todoId = savedStateHandle.get<Int>("todoId") ?: return

        if (todoId != -1) {
            viewModelScope.launch {
                fetchTodoById(todoId)
            }
        }
    }

    private suspend fun fetchTodoById(todoId: Int) {
        repository.getTodoById(todoId)?.let { fetchedTodo ->
            title = fetchedTodo.title
            description = fetchedTodo.description.orEmpty()
            todo = fetchedTodo
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChanged -> updateTitle(event.title)
            is AddEditTodoEvent.OnDescriptionChanged -> updateDescription(event.description)
            is AddEditTodoEvent.OnSaveTodoClick -> saveTodo()
        }
    }

    private fun updateTitle(newTitle: String) {
        title = newTitle
    }

    private fun updateDescription(newDescription: String) {
        description = newDescription
    }

    private fun saveTodo() {
        viewModelScope.launch {
            if (validateInput()) {
                try {
                    saveOrUpdateTodo()
                    sendUiEvent(UiEvent.PopBackStack)
                } catch (e: Exception) {
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            message = context.getString(R.string.error_saving_todo)
                        )
                    )
                }
            }
        }
    }

    private suspend fun saveOrUpdateTodo() {
        val newTodo = Todo(
            title = title,
            description = description,
            isDone = todo?.isDone ?: false,
            id = todo?.id ?: 0
        )
        repository.insertTodo(newTodo)
    }

    private fun validateInput(): Boolean {
        return if (title.isBlank()) {
            sendUiEvent(
                UiEvent.ShowSnackBar(
                    message = context.getString(R.string.required_title)
                )
            )
            false
        } else true
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
