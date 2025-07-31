package com.endava.beeq_components.organisms.button

sealed interface ButtonAction {

    class OnClick(private val onClick: () -> Unit = {}) : ButtonAction {
        fun onClick() {
            onClick.invoke()
        }
    }

    class CoroutineAction (
        private val onClick: suspend () -> Unit = {},
        private val onError: (Throwable) -> Unit = {},
        private val onSuccess: () -> Unit = {}
    ) : ButtonAction {
        suspend fun onClick() {
            onClick.invoke()
        }
        fun onError(error: Throwable) = onError.invoke(error)
        fun onSuccess() = onSuccess.invoke()
    }
}

