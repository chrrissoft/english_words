package com.chrrissoft.englishwords.auth.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chrrissoft.englishwords.R.string.*


@Composable
fun SingUpText(
    modifier: Modifier = Modifier
) {
    AuthTitle(
        modifier = modifier,
        specialWord = stringResource(special_singUp_word_auth_screen),
        text = stringResource(singUp_1_auth_screen),
        referProviderText = stringResource(auth_title_3_auth_screen)
    )
}

@Composable
fun SingInText(
    modifier: Modifier = Modifier
) {
    AuthTitle(
        modifier = modifier,
        specialWord = stringResource(special_singIn_word_auth_screen),
        text = stringResource(singIn_1_auth_screen),
        referProviderText = stringResource(auth_title_3_auth_screen)
    )
}

@Composable
fun AuthTitle(
    modifier: Modifier = Modifier,
    text: String,
    specialWord: String,
    referProviderText: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val size = (LocalConfiguration.current.screenWidthDp * .08).toInt()
        Row {
            Text(
                text = specialWord,
                style = typography
                    .displaySmall
                    .copy(
                        colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = size.sp
                    )
            )
            Text(
                text = text,
                style = typography
                    .displaySmall
                    .copy(
                        colorScheme.onSecondaryContainer,
                        fontSize = size.sp
                    )
            )
        }
        Text(
            text = referProviderText,
            style = typography
                .displaySmall
                .copy(colorScheme.secondary)
        )
    }
}
