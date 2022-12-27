package com.chrrissoft.englishwords.auth.framework

import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders.Email
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders.Email.Type.SingIn
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders.Email.Type.SingUp
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders.Google
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthResultState
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthResultState.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class FirebaseAuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthRepo {

    override fun auth(
        provider: AuthProviders, scope: CoroutineScope
    ): Flow<FirebaseAuthResultState> {

        return flow {
            emit(Loading)

            when (provider) {
                is Google<*> -> {
                    val credential = provider.credentials as SignInCredential
                    val token = credential.googleIdToken
                    val fireCredential = GoogleAuthProvider.getCredential(token, null)
                    auth.signInWithCredential(fireCredential).addOnSuccessListener {
                        scope.launch { emit(Success(it)) }
                    }.addOnCanceledListener {
                        scope.launch { emit(Cancel) }
                    }.addOnFailureListener {
                        scope.launch { emit(Error(it)) }
                    }
                }
                is Email -> {
                    when (provider.type) {
                        SingIn -> {
                            emit(Loading)
                            auth.createUserWithEmailAndPassword(provider.email, provider.pass)
                                .addOnSuccessListener {
                                    scope.launch { emit(Success(it)) }
                                }.addOnFailureListener {
                                    scope.launch { emit(Error(it)) }
                                }.addOnCanceledListener {
                                    scope.launch { emit(Cancel) }
                                }
                        }
                        SingUp -> {
                            emit(Loading)
                            auth.signInWithEmailAndPassword(provider.email, provider.pass)
                                .addOnSuccessListener {
                                    scope.launch { emit(Success(it)) }
                                }.addOnFailureListener {
                                    scope.launch { emit(Error(it)) }
                                }.addOnCanceledListener {
                                    scope.launch { emit(Cancel) }
                                }
                        }
                    }
                }
                is AuthProviders.Facebook<*> -> {
                    val credential = provider.credentials as LoginResult
                    val token = credential.accessToken.token
                    val fireCredential = FacebookAuthProvider.getCredential(token)
                    auth.signInWithCredential(fireCredential).addOnSuccessListener {
                        scope.launch { emit(Success(it)) }
                    }.addOnCanceledListener {
                        scope.launch { emit(Cancel) }
                    }.addOnFailureListener {
                        scope.launch { emit(Error(it)) }
                    }
                }
            }
        }
    }

    override fun logOut() {
        auth.signOut()
    }

}
