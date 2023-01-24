package com.chrrissoft.inglishwords.domian.gameplay

import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorState
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardState

data class GameState(
    val level: Steps,
    val words: Steps = Steps(),
    val failed: Int = 0,
    val end: Boolean = false,
    val translatedWord: String = "",
    val replacement: Boolean = false,
    val editor: EditorState = EditorState(),
    val keyboard: KeyboardState = KeyboardState()
)
