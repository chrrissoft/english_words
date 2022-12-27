package com.chrrissoft.englishwords.auth.ui.state

sealed interface AuthProviderResultState {
    object None : AuthProviderResultState
    object Cancel : AuthProviderResultState
    object Loading : AuthProviderResultState
    data class Error(val data: Throwable) : AuthProviderResultState
    data class Success<T>(val data: T) : AuthProviderResultState
}
