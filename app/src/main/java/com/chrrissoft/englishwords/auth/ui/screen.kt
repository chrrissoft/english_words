package com.chrrissoft.englishwords.auth.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chrrissoft.englishwords.auth.ui.components.*
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderCredential.FacebookCredential
import com.chrrissoft.englishwords.auth.ui.state.AuthProviderCredential.GoogleCredential
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState.AuthProvider.*
import com.chrrissoft.englishwords.auth.ui.state.Login.SingIn
import com.chrrissoft.englishwords.auth.ui.state.Login.SingUp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AuthScreen(
    activity: Activity,
    viewModel: AuthViewModel = hiltViewModel(),
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val colors = colorScheme.background

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setStatusBarColor(colors)
        systemUiController.setNavigationBarColor(colors)
        onDispose {}
    }


    val uiState by viewModel.uiState.collectAsState()

    when (uiState.provider) {
        Google -> {
            ShowOneTapScreen(
                activity = activity,
                client = viewModel.client,
                resultState = uiState.authProviderResultState,
                onSendGoogleCredential = { viewModel.authInFirebase(GoogleCredential(it)) }
            )
        }
        Facebook -> {
            FacebookAuth(
                activity = activity,
                manager = viewModel.loginManager,
                resultState = uiState.authProviderResultState,
                onSendCredential = { viewModel.authInFirebase(FacebookCredential(it)) }
            )
        }
        None -> { /* witting for user select anyone */
        }
    }

    FireBaseResultUi(uiState.firebaseAuthResultState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(15.dp)
    ) {
        when (uiState.login) {
            SingIn -> {
                SingIn(
                    state = uiState.singInEmailState,
                    onState = { viewModel.changeEmailSingInState(it) },
                    onValidate = { viewModel.beginEmailSingIn(it) },
                    onChooseFacebook = { viewModel.beginFacebookAuth() },
                    onChooseGoogle = { viewModel.beginGoogleAuth() },
                    onChangeToSingUp = { viewModel.chooseSingUp() }
                )
            }
            SingUp -> {
                SingUp(
                    state = uiState.singUpEmailState,
                    onState = { viewModel.changeEmailSingUpState(it) },
                    onBeginEmailAuth = { viewModel.beginEmailSingUp(it) },
                    onBeginFacebookAuth = { viewModel.beginFacebookAuth() },
                    onBeginGoogleAuth = { viewModel.beginGoogleAuth() },
                    onChangeToSingIn = { viewModel.chooseSingIn() }
                )
            }
        }
    }

}


