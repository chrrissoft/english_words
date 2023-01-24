package com.chrrissoft.englishwords.gameplay.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Editor(
    state: EditorState,
    translation: String,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        modifier = modifier,
        targetState = translation,
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var fontSize by remember {
                mutableStateOf(30.sp)
            }
            Box {
                Text(text = state.placeholder, color = colorScheme.surfaceVariant, fontSize = 55.sp)
                Text(text = state.userText, color = colorScheme.primary, fontSize = 55.sp)
            }
            Row(
                verticalAlignment = CenterVertically
            ) {
                Text(translation, fontSize = fontSize)
                Spacer(Modifier.width(10.dp))
                val color by if (state.mistakes == 0) {
                    animateColorAsState(targetValue = colorScheme.primary)
                } else {
                    animateColorAsState(targetValue = colorScheme.error)
                }
                Text(
                    color = color,
                    fontSize = fontSize,
                    text = "mistakes: 0",
                    onTextLayout = {
                        if (it.lineCount > 1) {
                            fontSize = fontSize.div(1.1f)
                        }
                    },
                )
            }
        }
    }
}
