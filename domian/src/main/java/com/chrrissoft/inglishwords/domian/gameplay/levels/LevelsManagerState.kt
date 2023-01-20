package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorState
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardState

data class LevelsManagerState(
    val level: Int= 0,
    val failed: Int = 0,
    val end: Boolean = false,
    val replacement: Boolean = false,
    val editor: EditorState = EditorState(),
    val keyboard: KeyboardState = KeyboardState(),
)
