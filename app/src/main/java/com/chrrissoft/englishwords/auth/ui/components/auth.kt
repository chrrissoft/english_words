package com.chrrissoft.englishwords.auth.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.chrrissoft.englishwords.R
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState.SingInEmailState
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState.SingUpEmailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.SingUp(
    state: SingUpEmailState,
    onBeginGoogleAuth: () -> Unit,
    onBeginFacebookAuth: () -> Unit,
    onState: (SingUpEmailState) -> Unit,
    onBeginEmailAuth: (SingUpEmailState) -> Unit,
    onChangeToSingIn: () -> Unit
) {
    SingUpText(
        Modifier
            .padding(bottom = 30.dp)
            .fillMaxWidth()
            .align { _, size, _ ->
                IntOffset(0, (size.height * .2).toInt())
            }
    )
    SingUpEmailAccount(
        state = state,
        onState = onState,
        onBeginAuth = onBeginEmailAuth,
        modifier = Modifier
            .fillMaxWidth()
            .align { _, size, _ ->
                IntOffset(0, (size.height * .4).toInt())
            }
    )
    AuthProvidersOptions(
        onChooseGoogle = { onBeginGoogleAuth() },
        onChooseFacebook = { onBeginFacebookAuth() },
        modifier = Modifier
            .fillMaxWidth()
            .align { _, size, _ ->
                IntOffset(0, (size.height * .75).toInt())
            }
    )
    ChangeAuthButton(
        buttonText = stringResource(R.string.change_to_singin_auth_screen),
        text = stringResource(R.string.there_account_auth_screen),
        modifier = Modifier.align { _, size, _ ->
            IntOffset(0, (size.height * .9).toInt())
        },
    ) { onChangeToSingIn() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.SingIn(
    state: SingInEmailState,
    onChooseGoogle: () -> Unit,
    onChooseFacebook: () -> Unit,
    onState: (SingInEmailState) -> Unit,
    onValidate: (SingInEmailState) -> Unit,
    onChangeToSingUp: () -> Unit
) {
    SingInText(
        Modifier
            .padding(bottom = 30.dp)
            .fillMaxWidth()
            .align { _, size, _ ->
                IntOffset(0, (size.height * .2).toInt())
            }
    )
    SingInEmailAccount(
        state = state,
        onState = onState,
        onBeginAuth = onValidate,
        modifier = Modifier
            .fillMaxWidth()
            .align { _, size, _ ->
                IntOffset(0, (size.height * .4).toInt())
            }
    )
    AuthProvidersOptions(
        onChooseGoogle = { onChooseGoogle() },
        onChooseFacebook = { onChooseFacebook() },
        modifier = Modifier
            .fillMaxWidth()
            .align { _, size, _ ->
                IntOffset(0, (size.height * .75).toInt())
            }
    )
    ChangeAuthButton(
        buttonText = stringResource(R.string.change_to_singup_auth_screen),
        modifier = Modifier.align { _, size, _ ->
            IntOffset(0, (size.height * .9).toInt())
        },
        text = stringResource(R.string.no_account_auth_screen)
    ) { onChangeToSingUp() }
}

@Composable
fun ChangeAuthButton(
    text: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 50.dp)
            .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally,
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = { onClick() },
            modifier = Modifier.fillMaxWidth()
        ) { Text(buttonText) }
    }
}