package com.chrrissoft.englishwords.auth.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.chrrissoft.englishwords.R.string.*
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState.SingInEmailState
import com.chrrissoft.englishwords.auth.ui.state.AuthScreenState.SingUpEmailState


@ExperimentalMaterial3Api
@Composable
fun SingInEmailAccount(
    state: SingInEmailState,
    onState: (SingInEmailState) -> Unit,
    onBeginAuth: (SingInEmailState) -> Unit,
    modifier: Modifier
) {
    SingInEmailAccount(
        modifier = modifier,
        email = state.email.text,
        onEmail = {
            val email = state.email.copy(text = it)
            onState(state.copy(email = email))
        },
        pass = state.pass.text,
        onPass = {
            val pass = state.pass.copy(text = it)
            onState(state.copy(pass = pass))
        },
        passError = state.pass.isInvalid,
        emailError = state.email.isInvalid,
        onVisible = {
            onState(state.copy(passIsVisible = it))
        },
        visible = state.passIsVisible,
        onBeginAuth = { onBeginAuth(state) }
    )
}

@ExperimentalMaterial3Api
@Composable
fun SingInEmailAccount(
    modifier: Modifier = Modifier,
    email: String,
    onEmail: (String) -> Unit,
    pass: String,
    onPass: (String) -> Unit,
    passError: Boolean,
    emailError: Boolean,
    onVisible: (Boolean) -> Unit,
    visible: Boolean,
    onBeginAuth: () -> Unit,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally
    ) {
        EmailInput(
            email = email,
            error = emailError,
            onEmail = onEmail
        )
        Spacer(Modifier.height(25.dp))
        PasswordInput(
            pass = pass,
            onPass = onPass,
            onVisible = onVisible,
            visible = visible,
            error = passError,
            text = stringResource(pass_placeholder_auth_screen),
        )
        Spacer(Modifier.height(20.dp))
        BeginAuthButton { onBeginAuth() }
    }

}


@ExperimentalMaterial3Api
@Composable
fun SingUpEmailAccount(
    state: SingUpEmailState,
    onState: (SingUpEmailState) -> Unit,
    onBeginAuth: (SingUpEmailState) -> Unit,
    modifier: Modifier
) {

    SingUpEmailAccount(
        modifier = modifier,
        email = state.email.text,
        emailError = state.email.isInvalid,
        onEmail = {
            val email = state.email.copy(text = it)
            onState(state.copy(email = email))
        },
        pass1 = state.pass1.text,
        onPass1 = {
            val pass = state.pass1.copy(text = it)
            onState(state.copy(pass1 = pass))
        },
        pass2 = state.pass2.text,
        onPass2 = {
            val pass = state.pass2.copy(text = it)
            onState(state.copy(pass2 = pass))
        },
        invalidPass = state.pass1.isInvalid,
        unequalPass = state.pass2.isInvalid,
        onVisible = {
            onState(state.copy(passIsVisible = it))
        },
        visible = state.passIsVisible,
        onBeginAuth = { onBeginAuth(state) }
    )
}

@ExperimentalMaterial3Api
@Composable
fun SingUpEmailAccount(
    email: String,
    emailError: Boolean,
    onEmail: (String) -> Unit,
    pass1: String,
    onPass1: (String) -> Unit,
    pass2: String,
    onPass2: (String) -> Unit,
    invalidPass: Boolean,
    unequalPass: Boolean,
    onVisible: (Boolean) -> Unit,
    visible: Boolean,
    modifier: Modifier = Modifier,
    onBeginAuth: () -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally
    ) {
        EmailInput(
            email = email,
            error = emailError,
            onEmail = onEmail
        )
        Spacer(Modifier.height(25.dp))
        PasswordInput(
            pass = pass1,
            onPass = onPass1,
            onVisible = onVisible,
            visible = visible,
            text = stringResource(pass_placeholder_auth_screen),
            error = invalidPass
        )
        Spacer(Modifier.height(15.dp))
        PasswordInput(
            pass = pass2,
            onPass = onPass2,
            onVisible = onVisible,
            visible = visible,
            text = stringResource(repeat_pass_placeholder_singUp_auth_screen),
            error = unequalPass
        )
        Spacer(Modifier.height(20.dp))
        BeginAuthButton { onBeginAuth() }
    }

}

@ExperimentalMaterial3Api
@Composable
fun EmailInput(
    email: String,
    error: Boolean,
    modifier: Modifier = Modifier,
    onEmail: (String) -> Unit
) {
    TextField(
        value = email,
        isError = error,
        textStyle = TextStyle(
            fontWeight = FontWeight(600),
            color = if (error)
                colorScheme.onError else colorScheme.onSecondary,
        ),
        colors = textFieldColors(
            containerColor = if (error) colorScheme.error else colorScheme.secondary,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = colorScheme.onSecondary,
            errorCursorColor = colorScheme.onError,
            errorIndicatorColor = colorScheme.onError,
        ),
        onValueChange = { onEmail(it) },
        placeholder = {
            Text(
                text = stringResource(email_placeholder_auth_screen),
                color = if (error)
                    colorScheme.onError else colorScheme.onSecondary,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier
            .padding(horizontal = 50.dp)
            .fillMaxWidth()
            .clip(shapes.medium)
    )
}

@ExperimentalMaterial3Api
@Composable
fun PasswordInput(
    pass: String,
    onPass: (String) -> Unit,
    onVisible: (Boolean) -> Unit,
    visible: Boolean,
    error: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = pass,
        isError = error,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { onPass(it) },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = {
            Text(
                text = text,
                color = if (error)
                    colorScheme.onError else colorScheme.onSecondary,
                fontWeight = FontWeight.Bold
            )
        },
        trailingIcon = {
            val color = if (error) colorScheme.onError else colorScheme.onSecondary
            val image = if (visible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            IconButton(onClick = { onVisible(!visible) }) {
                Icon(imageVector = image, null, tint = color)
            }
        },
        textStyle = TextStyle(
            fontWeight = FontWeight(600),
            color = if (error)
                colorScheme.onError else colorScheme.onSecondary,
        ),
        colors = textFieldColors(
            containerColor = if (error) colorScheme.error else colorScheme.secondary,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = colorScheme.onSecondary,
            errorCursorColor = colorScheme.onError,
            errorIndicatorColor = colorScheme.onError,
        ),
        modifier = modifier
            .padding(horizontal = 50.dp)
            .fillMaxWidth()
            .clip(shapes.medium)
    )
}

@Composable
fun BeginAuthButton(
    modifier: Modifier = Modifier,
    onBeginAuth: () -> Unit
) {
    Button(
        onClick = { onBeginAuth() },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Text(text = stringResource(auth_email_auth_screen))
    }
}