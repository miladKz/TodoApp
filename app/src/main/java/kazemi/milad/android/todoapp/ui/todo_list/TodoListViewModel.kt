package kazemi.milad.android.todoapp.ui.todo_list

import androidx.lifecycle.ViewModel
import jakarta.inject.Inject
import kazemi.milad.android.todoapp.data.TodoRepository
import kazemi.milad.android.todoapp.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    val todos = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
}