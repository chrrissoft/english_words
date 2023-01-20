package com.chrrissoft.englishwords.auth.framework

import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders.*
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthResultState
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthResultState.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class FirebaseAuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthRepo {

    override fun authWithProvider(
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
                        scope.launch(IO) { emit(Success(it)) }
                    }.addOnCanceledListener {
                        scope.launch(IO) { emit(Cancel) }
                    }.addOnFailureListener {
                        scope.launch(IO) { emit(Error(it)) }
                    }
                }
                is Facebook<*> -> {
                    val credential = provider.credentials as LoginResult
                    val token = credential.accessToken.token
                    val fireCredential = FacebookAuthProvider.getCredential(token)
                    auth.signInWithCredential(fireCredential).addOnSuccessListener {
                        scope.launch(IO) { emit(Success(it)) }
                    }.addOnCanceledListener {
                        scope.launch(IO) { emit(Cancel) }
                    }.addOnFailureListener {
                        scope.launch(IO) { emit(Error(it)) }
                    }
                }
            }
        }
    }

    override fun singUpEmail(
        user: String,
        pass: String,
        scope: CoroutineScope
    ): Flow<FirebaseAuthResultState> {
        return flow {
            emit(Loading)
            auth.createUserWithEmailAndPassword(user, pass)
                .addOnSuccessListener { scope.launch(IO) { emit(Success(it)) } }
                .addOnFailureListener { scope.launch(IO) { emit(Error(it)) } }
                .addOnCanceledListener { scope.launch(IO) { emit(Cancel) } }
        }
    }

    override fun singInEmail(
        user: String,
        pass: String,
        scope: CoroutineScope
    ): Flow<FirebaseAuthResultState> {
        return flow {
            emit(Loading)
            auth.signInWithEmailAndPassword(user, pass)
                .addOnSuccessListener { scope.launch(IO) { emit(Success(it)) } }
                .addOnFailureListener { scope.launch(IO) { emit(Error(it)) } }
                .addOnCanceledListener { scope.launch(IO) { emit(Cancel) } }
        }
    }

    override fun logOut() {
        auth.signOut()
    }

}
