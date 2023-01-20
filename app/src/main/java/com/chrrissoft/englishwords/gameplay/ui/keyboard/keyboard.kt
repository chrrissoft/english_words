package com.chrrissoft.englishwords.gameplay.ui.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Key

private const val KEYBOARD_HEIGHT_PERCENTAGE = 3.4

@Composable
fun Keyboard(
    keys: List<Key>,
    modifier: Modifier = Modifier,
) {

    val keyboardHeight = LocalConfiguration.current
        .screenHeightDp.div(KEYBOARD_HEIGHT_PERCENTAGE).dp
    val keyboardWidth = LocalConfiguration.current.screenWidthDp.dp

    val keyHeight = keyboardHeight.div(4)
    val keyWidth = keyboardWidth.div(10)
    val deleteKeyWidth = keyboardWidth.div(7)
    val magicKeyWidth = keyboardWidth.div(7)

    Box(
        modifier = modifier
            .width(keyboardWidth)
            .height(keyboardHeight)
            .background(colorScheme.surfaceVariant)
    ) {
        Keys(
            keys = keys,
            height = keyHeight,
            width = keyWidth,
            magicWidth = magicKeyWidth,
            deleteWidth = deleteKeyWidth,
            spacerWidth = keyboardWidth,
        )
    }
}
