package com.chrrissoft.englishwords.auth.ui.state

import com.google.firebase.auth.AuthResult

interface FirebaseAuthResultState {
    object None : FirebaseAuthResultState
    object Cancel : FirebaseAuthResultState
    object Loading : FirebaseAuthResultState
    data class Success(val data: AuthResult) : FirebaseAuthResultState
    data class Error(val data: Throwable) : FirebaseAuthResultState
}