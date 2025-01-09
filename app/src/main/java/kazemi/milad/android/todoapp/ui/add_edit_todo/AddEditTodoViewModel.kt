package kazemi.milad.android.todoapp.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kazemi.milad.android.todoapp.data.Todo
import kazemi.milad.android.todoapp.data.TodoRepository
import kazemi.milad.android.todoapp.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
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
        val todoId = savedStateHandle.get<Int>("todoId")!!

        if (todoId != -1) {
            viewModelScope.launch {
                val todo = repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChanged -> title = event.title
            is AddEditTodoEvent.OnDescriptionChanged -> {
                description = event.description
            }

            is AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isNotBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                                message = "title cant be empty"
                            )
                        )
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id?:0
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}