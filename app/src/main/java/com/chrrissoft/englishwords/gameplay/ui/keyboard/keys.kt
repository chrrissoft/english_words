package com.chrrissoft.englishwords.gameplay.ui.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Key
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Key.*

@Composable
private fun Key(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    background: Color = colorScheme.background,
    content: @Composable BoxScope.() -> Unit,
    onPress: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(width)
            .height(height)
            .padding(3.dp, 4.dp)
            .shadow(2.dp, shapes.small)
            .clickable { onPress() }
            .clip(shapes.small)
            .background(background)
    ) {
        content(this)
    }
}

@Composable
private fun TextKey(
    width: Dp,
    height: Dp,
    text: String,
    onPress: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = colorScheme.background,
) {


    Key(
        width = width,
        height = height,
        modifier = modifier,
        background = background,
        onPress = { onPress() },
        content = { KeyText(text) },
    )
}

@Composable
private fun KeyText(text: String) {
    Text(
        text = text,
        color = colorScheme.surfaceTint,
        fontWeight = FontWeight.Medium
    )
}

@Composable
private fun TextLimitedKey(
    width: Dp,
    height: Dp,
    count: Int,
    text: String,
    onPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Key(
        width = width,
        height = height,
        modifier = modifier,
        content = {
            Text(
                "$count",
                fontSize = 10.sp,
                modifier = Modifier
                    .align(TopEnd)
                    .padding(top = 2.dp, end = 2.dp),
                color = colorScheme.surfaceTint,
                fontWeight = FontWeight.Medium,
            )
            KeyText(text)
        },
        onPress = { onPress() },
    )
}

@Composable
fun MagicKey(
    width: Dp,
    height: Dp,
    count: Int,
    onPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val background = if (count == 0) colorScheme.errorContainer else colorScheme.primaryContainer
    val color = if (count == 0) colorScheme.onErrorContainer else colorScheme.onPrimaryContainer
    Key(
        width = width,
        height = height,
        modifier = modifier,
        background = background,
        content = {
            Text(
                color = color,
                text = count.toString(),
                fontWeight = FontWeight.Medium
            )
        },
        onPress = { onPress() },
    )
}

@Composable
private fun DeleteKey(
    width: Dp,
    height: Dp,
    onPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Key(
        width = width,
        height = height,
        modifier = modifier,
        content = {
            Icon(Icons.Rounded.Backspace, null)
        },
        onPress = { onPress() },
    )
}

@Composable
private fun SelectableKey(
    width: Dp,
    height: Dp,
    text: String,
    selected: Boolean,
    onPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color = if (selected) colorScheme.errorContainer
    else colorScheme.background
    TextKey(
        text = text,
        width = width,
        height = height,
        onPress = onPress,
        background = color,
        modifier = modifier
    )
}

@Composable
fun SpacerKey(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
) {
    Key(
        width = width,
        height = height,
        modifier = modifier,
        content = { },
        onPress = { },
    )
}

@Composable
fun BoxScope.Keys(
    width: Dp,
    height: Dp,
    deleteWidth: Dp,
    magicWidth: Dp,
    spacerWidth: Dp,
    keys: List<Key<*>>,
) {
    keys.forEach {
        when (it) {
            is DeleteKey -> {
                DeleteKey(
                    width = deleteWidth,
                    modifier = Modifier.align { _, size, _ ->
                        IntOffset(
                            x = size.width.times(it.coordinates.x).toInt(),
                            y = size.height.times(it.coordinates.y).toInt()
                        )
                    },
                    height = height, onPress = { it.onClick() },
                )
            }
            is TextLimitedKey -> {
                TextLimitedKey(
                    width = width,
                    height = height,
                    modifier = Modifier.align { _, size, _ ->
                        IntOffset(
                            x = size.width.times(it.coordinates.x).toInt(),
                            y = size.height.times(it.coordinates.y).toInt()
                        )
                    },
                    count = it.stack.size,
                    text = it.text,
                    onPress = { it.onClick() },
                )
            }
            is TextKey -> {
                TextKey(
                    width = width,
                    height = height,
                    modifier = Modifier.align { _, size, _ ->
                        IntOffset(
                            x = size.width.times(it.coordinates.x).toInt(),
                            y = size.height.times(it.coordinates.y).toInt()
                        )
                    },
                    text = it.text,
                    onPress = { it.onClick() },
                )
            }
            is MagicKey -> {
                MagicKey(
                    width = magicWidth,
                    height = height,
                    count = it.breaks,
                    modifier = Modifier.align { _, size, _ ->
                        IntOffset(
                            x = size.width.times(it.coordinates.x).toInt(),
                            y = size.height.times(it.coordinates.y).toInt()
                        )
                    },
                    onPress = { it.onClick() },
                )
            }
            is SpacerKey -> {
                SpacerKey(
                    height = height,
                    width = spacerWidth,
                    modifier = Modifier.align { _, size, _ ->
                        IntOffset(
                            x = size.width.times(it.coordinates.x).toInt(),
                            y = size.height.times(it.coordinates.y).toInt()
                        )
                    },
                )
            }
            is SelectableKey -> {
                SelectableKey(
                    width = width,
                    height = height,
                    selected = it.selected,
                    modifier = Modifier.align { _, size, _ ->
                        IntOffset(
                            x = size.width.times(it.coordinates.x).toInt(),
                            y = size.height.times(it.coordinates.y).toInt()
                        )
                    },
                    text = it.text, onPress = { it.onClick() },
                )
            }
        }
    }
}
