package com.chrrissoft.englishwords.gameplay.ui

import com.chrrissoft.inglishwords.domian.gameplay.Steps
import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorState
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardState

data class GamePlayScreenState(
    val level: Steps = Steps(),
    val words: Steps = Steps(),
    val failures: Int = 0,
    val translated: String = "",
    val replacement: Boolean = false,
    val editor: EditorState = EditorState(),
    val keyboard: KeyboardState = KeyboardState(emptyList())
)
