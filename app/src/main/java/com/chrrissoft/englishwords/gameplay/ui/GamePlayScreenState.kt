package com.chrrissoft.englishwords.gameplay.ui

import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorState
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardState

data class GamePlayScreenState(
    val level: Int = 0,
    val failed: Int = 0,
    val replacement: Boolean = false,
    val editor: EditorState = EditorState(),
    val keyboard: KeyboardState = KeyboardState(emptyList())
)
