package bluevelvet.composents.pinview.view

sealed class PinState {
    object Default : PinState()
    object Loading : PinState()
    object Error : PinState()
    object Success : PinState()
}