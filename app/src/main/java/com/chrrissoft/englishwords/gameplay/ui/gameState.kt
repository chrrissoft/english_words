package com.chrrissoft.englishwords.gameplay.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun GameState(
    state: GamePlayScreenState
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.secondaryContainer),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(fontSize = 18.sp, text = "word ${state.words.current} of ${state.words.total}")
            Text(fontSize = 18.sp, text = "failures ${state.failures}")
        }
        AnimatedVisibility(visible = state.replacement) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.secondary),
            ) {
                Text(text = "In replacement", color = colorScheme.onSecondary)
            }
        }
    }
}
