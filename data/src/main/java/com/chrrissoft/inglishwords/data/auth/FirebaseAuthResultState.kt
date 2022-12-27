package com.chrrissoft.inglishwords.data.auth

sealed interface FirebaseAuthResultState {
    data class Success<T>(val data: T) : FirebaseAuthResultState
    data class Error(val data: Throwable) : FirebaseAuthResultState
    object Loading : FirebaseAuthResultState
    object Cancel : FirebaseAuthResultState
}