package com.chrrissoft.inglishwords.data.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepo {

    sealed interface AuthProviders {
        data class Google<T>(val credentials: T) : AuthProviders
        data class Facebook<T>(val credentials: T) : AuthProviders
        data class Email(
            val email: String,
            val pass: String,
            val type: Type
        ) : AuthProviders { enum class Type { SingIn, SingUp } }
    }

    fun auth(
        provider: AuthProviders,
        scope: CoroutineScope
    ): Flow<FirebaseAuthResultState>

    fun logOut()
}

