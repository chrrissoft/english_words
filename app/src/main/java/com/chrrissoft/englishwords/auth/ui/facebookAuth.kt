package com.chrrissoft.englishwords.auth.ui

import androidx.compose.runtime.Composable
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState
import com.facebook.login.LoginResult

@Composable
fun FacebookAuth(
    resultState: AuthProviderResultState,
    onSendCredential: (LoginResult) -> Unit,
) {
    when (resultState) {
        AuthProviderResultState.None -> {  }
        AuthProviderResultState.Cancel -> {  }
        AuthProviderResultState.Loading -> {  }
        is AuthProviderResultState.Error -> {  }
        is AuthProviderResultState.Success<*> -> {
            resultState.data as LoginResult
            onSendCredential(resultState.data)
        }
    }

}