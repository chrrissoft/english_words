package com.chrrissoft.englishwords.auth.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import com.chrrissoft.englishwords.R.drawable.goolge_auth
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chrrissoft.englishwords.R.drawable.auth_facebook

@Composable
fun AuthProvidersOptions(
    modifier: Modifier = Modifier,
    onChooseGoogle: () -> Unit,
    onChooseFacebook: () -> Unit
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            painter = painterResource(id = goolge_auth),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .clickable { onChooseGoogle() }
        )

        Spacer(modifier = Modifier.width(30.dp))

        Image(
            painter = painterResource(id = auth_facebook),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .clickable { onChooseFacebook() }
        )

    }
}
