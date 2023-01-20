package com.chrrissoft.inglishwords.data.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepo {

    sealed interface AuthProviders {
        data class Google<T>(val credentials: T) : AuthProviders
        data class Facebook<T>(val credentials: T) : AuthProviders
    }

    fun authWithProvider(
        provider: AuthProviders,
        scope: CoroutineScope
    ): Flow<FirebaseAuthResultState>

    fun singUpEmail(
        user: String, pass: String, scope: CoroutineScope
    ) : Flow<FirebaseAuthResultState>

    fun singInEmail(
        user: String, pass: String, scope: CoroutineScope
    ) : Flow<FirebaseAuthResultState>

    fun logOut()
}

