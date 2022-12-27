package com.chrrissoft.englishwords.auth.ui

import android.app.Activity
import android.content.IntentSender
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

@Composable
fun FacebookAuth(
    activity: Activity,
    manager: LoginManager,
    resultState: AuthProviderResultState,
    onSendCredential: (LoginResult) -> Unit,
) {
    when (resultState) {
        AuthProviderResultState.None -> TODO()
        AuthProviderResultState.Cancel -> TODO()
        AuthProviderResultState.Loading -> TODO()
        is AuthProviderResultState.Error -> TODO()
        is AuthProviderResultState.Success<*> -> {
            resultState.data as LoginResult
            onSendCredential(resultState.data)
        }
    }

}