package com.chrrissoft.englishwords.auth.framework

import com.chrrissoft.englishwords.di.SingInRequest
import com.chrrissoft.englishwords.di.SingUpRequest
import com.chrrissoft.inglishwords.data.auth.GoogleAuthRepo
import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GoogleAuthRepoImpl @Inject constructor(
    private val client: SignInClient,
    @SingInRequest
    private val singInRequest: BeginSignInRequest,
    @SingUpRequest
    private val singUpRequest: BeginSignInRequest,
) : GoogleAuthRepo {

    override fun singIn(
        scope: CoroutineScope
    ): Flow<AuthProviderResultState> {
        return flow {
            emit(AuthProviderResultState.Loading)

            client.beginSignIn(singInRequest).addOnSuccessListener {
                scope.launch { emit(AuthProviderResultState.Success(it)) }
            }.addOnCanceledListener {
                scope.launch { emit(AuthProviderResultState.Cancel) }
            }.addOnFailureListener {
                scope.launch { emit(AuthProviderResultState.Error(it)) }
            }
        }
    }

    override fun singUp(
        scope: CoroutineScope
    ): Flow<AuthProviderResultState> {
        return flow {
            emit(AuthProviderResultState.Loading)
            println("begin auth")

            client.beginSignIn(singUpRequest).addOnSuccessListener {
                scope.launch { emit(AuthProviderResultState.Success(it)) }
            }

            client.beginSignIn(singUpRequest).addOnCanceledListener {
                scope.launch { emit(AuthProviderResultState.Cancel) }
            }

            client.beginSignIn(singUpRequest).addOnFailureListener {
                scope.launch { emit(AuthProviderResultState.Error(it)) }
            }
        }
    }

}
