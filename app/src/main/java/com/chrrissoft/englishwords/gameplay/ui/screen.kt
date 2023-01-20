package com.chrrissoft.englishwords.gameplay.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.chrrissoft.englishwords.gameplay.ui.keyboard.Keyboard

@Composable
fun GamePlayScreen(
    viewModel: GamePlayViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Column {
        Text(text = state.editor.userText)
        Text(text = state.editor.placeholder)
        Text(text = "failed: ${state.failed}")
        Text(text = "mistakes: ${state.editor.mistakes}")
        Text(text = "replacement: ${state.replacement}")
        Text(text = "level: ${state.level}")

        Keyboard(state.keyboard.structure)
    }
}
