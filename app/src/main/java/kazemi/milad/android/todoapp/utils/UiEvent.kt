package kazemi.milad.android.todoapp.utils

sealed class UiEvent {
    data object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnaSnackBar(
        val message: String,
        val action: String? = null
    ) : UiEvent()
}