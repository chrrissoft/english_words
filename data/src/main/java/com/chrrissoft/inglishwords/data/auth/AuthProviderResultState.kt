package com.chrrissoft.inglishwords.data.auth

sealed interface AuthProviderResultState {
    data class Success<T>(val data: T) : AuthProviderResultState
    data class Error(val data: Throwable) : AuthProviderResultState
    object Loading : AuthProviderResultState
    object Cancel : AuthProviderResultState
}
