package com.chrrissoft.englishwords.auth.framework

import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState
import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState.*
import com.chrrissoft.inglishwords.data.auth.FacebookAuthRepo
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FacebookAuthRepoImpl @Inject constructor(
    private val manager: LoginManager,
    private val callback: CallbackManager
) : FacebookAuthRepo {

    override fun auth(scope: CoroutineScope): Flow<AuthProviderResultState> {
        return flow {
            emit(Loading)
            manager.registerCallback(callback, object : FacebookCallback<LoginResult> {

                override fun onCancel() {
                    scope.launch { emit(Cancel) }
                }

                override fun onError(error: FacebookException) {
                    scope.launch { emit(Error(error)) }
                }

                override fun onSuccess(result: LoginResult) {
                    scope.launch { emit(Success(result)) }
                    manager.unregisterCallback(callback)
                }

            })
        }
    }

}