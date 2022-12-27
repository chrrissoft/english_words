package com.chrrissoft.englishwords.auth.ui

import android.app.Activity
import android.content.IntentSender
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderResultState.*
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

private const val TAG = "ShowOneTapScreen"

@Composable
fun ShowOneTapScreen(
    activity: Activity,
    client: SignInClient,
    resultState: AuthProviderResultState,
    onSendGoogleCredential: (SignInCredential) -> Unit,
) {
    when (resultState) {
        None -> { Log.d(TAG, "None") }
        Cancel -> { Log.d(TAG, "Cancel") }
        Loading -> { Log.d(TAG, "Loading") }
        is Error -> { Log.d(TAG, "Error") }
        is Success<*> -> {
            Log.d(TAG, "Success")
//            try {
//                resultState.data as BeginSignInResult
//                val intentSender = resultState.data.pendingIntent.intentSender
//                startIntentSenderForResult(
//                    activity, intentSender, reqOneTap(),
//                    null, 0, 0, 0, null
//                )
//            } catch (_: IntentSender.SendIntentException) {
//            }
        }
    }

    SendGoogleCredentialsToFirebase(
        client = client,
        sendCredential = { onSendGoogleCredential(it) }
    )
}

@Composable
private fun SendGoogleCredentialsToFirebase(
    client: SignInClient,
    sendCredential: (SignInCredential) -> Unit,
) {
    rememberLauncherForActivityResult(StartIntentSenderForResult()) {
        val credential: SignInCredential = client.getSignInCredentialFromIntent(it.data)
        sendCredential(credential)
    }
}

private fun reqOneTap() = System.currentTimeMillis().toInt()
