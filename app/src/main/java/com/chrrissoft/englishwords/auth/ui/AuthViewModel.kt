package com.chrrissoft.englishwords.auth.ui

import androidx.lifecycle.ViewModel
import com.facebook.CallbackManager
import androidx.lifecycle.viewModelScope
import com.chrrissoft.inglishwords.data.auth.FacebookAuthRepo
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderCredential
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState.Loading
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState.Success
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState.Error
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState.Cancel
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderCredential.GoogleCredential as UiGoogle
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderCredential.FacebookCredential as UiFacebook
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState.*
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState.AuthProvider.*
import com.chrrissoft.englishwords.auth.ui.state.Login.SingIn
import com.chrrissoft.englishwords.auth.ui.state.Login.SingUp
import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState as DataAuthProviderResultState
import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState.Success as DataSuccess
import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState.Loading as DataLoading
import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState.Cancel as DataCancel
import com.chrrissoft.inglishwords.data.auth.AuthProviderResultState.Error as DataError
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders.Google as DataGoogle
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo.AuthProviders.Facebook as DataFacebook
import com.chrrissoft.inglishwords.data.auth.GoogleAuthRepo
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val client: SignInClient,
    val loginManager: LoginManager,
    val callbackManager: CallbackManager,
    private val googleAuthRepo: GoogleAuthRepo,
    private val firebaseAuthRepo: FirebaseAuthRepo,
    private val facebookAuthRepo: FacebookAuthRepo,
) : ViewModel() {

    private companion object {
        private const val TAG = "AuthViewModel"
    }

    private val _uiState = MutableStateFlow(AuthScreenState())
    val uiState = _uiState.asStateFlow()

    private fun googleSingIn() {
        viewModelScope.launch {
            googleAuthRepo
                .singIn(this)
                .collect { updateGoogleAuthState(it) }
        }
    }

    private fun googleSingUp() {
        viewModelScope.launch {
            googleAuthRepo
                .singUp(this)
                .collect { updateGoogleAuthState(it) }
        }
    }

    fun chooseSingIn() {
        _uiState.update { it.copy(login = SingIn) }
    }

    fun chooseSingUp() {
        _uiState.update { it.copy(login = SingUp) }
    }

    fun beginGoogleAuth() {
        _uiState.update { it.copy(provider = Google) }
        when (_uiState.value.login) {
            SingIn -> googleSingIn()
            SingUp -> googleSingUp()
        }
    }

    fun beginFacebookAuth() {
        _uiState.update { it.copy(provider = Facebook) }
        viewModelScope.launch {
            facebookAuthRepo
                .auth(this)
                .transform<DataAuthProviderResultState, Nothing> {
                    updateFacebookAuthState(it)
                }
        }
    }

    fun beginEmailSingIn(state: SingInEmailState) {
        if (!validateEmailSingInState(state)) return
        singInEmail(state.email.text, state.pass.text)
    }

    fun beginEmailSingUp(state: SingUpEmailState) {
        if (!validateEmailSingUpState(state)) return
        singUpEmail(state.email.text, state.pass2.text)
    }

    fun authInFirebaseWithProvider(provider: AuthProviderCredential) {
        viewModelScope.launch {
            when (provider) {
                is UiFacebook -> {
                    firebaseAuthRepo
                        .authWithProvider(DataFacebook(provider.credential), this)
                        .collect { TODO() }
                }
                is UiGoogle -> {
                    firebaseAuthRepo
                        .authWithProvider(DataGoogle(provider.credential), this)
                        .collect { TODO() }
                }
            }
        }
    }

    private fun singInEmail(user: String, pass: String) {
        viewModelScope.launch {
            firebaseAuthRepo
                .singInEmail(user, pass, this)
                .collect { TODO() }
        }
    }

    private fun singUpEmail(user: String, pass: String) {
        viewModelScope.launch {
            firebaseAuthRepo
                .singUpEmail(user, pass, this)
                .collect { TODO() }
        }
    }

    private fun updateGoogleAuthState(authState: DataAuthProviderResultState) {
        when (authState) {
            DataCancel -> {
                _uiState.update { it.copy(authProviderResultState = Cancel) }
            }
            is DataError -> {
                _uiState.update { it.copy(authProviderResultState = Error(authState.data)) }
            }
            DataLoading -> {
                _uiState.update { it.copy(authProviderResultState = Loading) }
            }
            is DataSuccess<*> -> {
                updateGoogleAuthSuccess(authState.data as BeginSignInResult)
            }
        }
    }

    private fun updateFacebookAuthState(authState: DataAuthProviderResultState) {
        when (authState) {
            DataCancel -> {
                _uiState.update { it.copy(authProviderResultState = Cancel) }
            }
            is DataError -> {
                _uiState.update { it.copy(authProviderResultState = Error(authState.data)) }
            }
            DataLoading -> {
                _uiState.update { it.copy(authProviderResultState = Loading) }
            }
            is DataSuccess<*> -> {
                updateFacebookAuthSuccess(authState.data as LoginResult)
            }
        }
    }

    private fun updateGoogleAuthSuccess(result: BeginSignInResult) {
        _uiState.update { it.copy(authProviderResultState = Success(result)) }
    }

    private fun updateFacebookAuthSuccess(result: LoginResult) {
        _uiState.update { it.copy(authProviderResultState = Success(result)) }
    }

    fun changeEmailSingUpState(email: SingUpEmailState) {
        _uiState.update { it.copy(singUpEmailState = email) }
    }

    private fun validateEmailSingUpState(state: SingUpEmailState): Boolean {
        var isValid = true
        validateEmailSinUp(state.email) { isValid = false }
        validatePassSecurity(state.pass1) { isValid = false }
        validatePassEquality(state) { isValid = false }
        return isValid
    }

    private fun validateEmailSingInState(state: SingInEmailState): Boolean {
        var isValid = true
        validateEmailSinIn(state.email) { isValid = false }
        validatePassSinIn(state.pass) { isValid = false }
        return isValid
    }

    private fun validateEmailSinUp(
        email: EmailData,
        onValid: () -> Unit = {},
        onInvalid: () -> Unit,
    ) {
        if (email.text.isEmpty()) {
            onInvalid()
            _uiState.update {
                val invalid = it
                    .singUpEmailState
                    .email.copy(isInvalid = true)
                it.copy(singUpEmailState = it.singUpEmailState.copy(email = invalid))
            }
        } else {
            onValid()
            _uiState.update {
                val invalid = it
                    .singUpEmailState
                    .email.copy(isInvalid = false)
                it.copy(singUpEmailState = it.singUpEmailState.copy(email = invalid))
            }
        }
    }

    private fun validateEmailSinIn(
        email: EmailData,
        onValid: () -> Unit = {},
        onInvalid: () -> Unit,
    ) {
        if (email.text.isEmpty()) {
            onInvalid()
            _uiState.update {
                val invalid = it
                    .singInEmailState
                    .email.copy(isInvalid = true)
                it.copy(singInEmailState = it.singInEmailState.copy(email = invalid))
            }
        } else {
            onValid()
            _uiState.update {
                val invalid = it
                    .singInEmailState
                    .email.copy(isInvalid = false)
                it.copy(singInEmailState = it.singInEmailState.copy(email = invalid))
            }
        }
    }

    private fun validatePassSinIn(
        pass: PassData,
        onValid: () -> Unit = {},
        onInvalid: () -> Unit,
    ) {
        if (pass.text.isEmpty()) {
            onInvalid()

            _uiState.update {
                val invalid = it
                    .singInEmailState
                    .pass.copy(isInvalid = true)
                it.copy(singInEmailState = it.singInEmailState.copy(pass = invalid))
            }
        } else {
            onValid()
            _uiState.update {
                val invalid = it
                    .singInEmailState
                    .pass.copy(isInvalid = false)
                it.copy(singInEmailState = it.singInEmailState.copy(pass = invalid))
            }
        }
    }

    private fun validatePassSecurity(
        pass: PassData,
        onValid: () -> Unit = {},
        onInvalid: () -> Unit,
    ) {
        if (pass.text.isEmpty()) {
            onInvalid()
            _uiState.update {
                val invalidPass = it.singUpEmailState.pass1.copy(isInvalid = true)
                val newState = it.singUpEmailState.copy(pass1 = invalidPass)
                it.copy(singUpEmailState = newState)
            }
        } else {
            onValid()
            _uiState.update {
                val invalidPass = it.singUpEmailState.pass1.copy(isInvalid = false)
                val newState = it.singUpEmailState.copy(pass1 = invalidPass)
                it.copy(singUpEmailState = newState)
            }
        }
    }

    private fun validatePassEquality(
        state: SingUpEmailState,
        onValid: () -> Unit = {},
        onInvalid: () -> Unit,
    ) {
        if (state.pass1.text != state.pass2.text) {
            onInvalid()
            _uiState.update {
                val invalidPass = it.singUpEmailState.pass2.copy(isInvalid = true)
                val newState = it.singUpEmailState.copy(pass2 = invalidPass)
                it.copy(singUpEmailState = newState)
            }
        } else {
            onValid()
            _uiState.update {
                val invalidPass = it.singUpEmailState.pass2.copy(isInvalid = false)
                val newState = it.singUpEmailState.copy(pass2 = invalidPass)
                it.copy(singUpEmailState = newState)
            }
        }
    }

    fun changeEmailSingInState(email: SingInEmailState) {
        _uiState.update { it.copy(singInEmailState = email) }
    }

}
