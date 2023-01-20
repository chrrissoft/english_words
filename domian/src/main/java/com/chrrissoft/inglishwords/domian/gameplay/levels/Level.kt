package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorReport
import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorState
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardState
import kotlinx.coroutines.flow.StateFlow

interface Level {

    val editorState: StateFlow<EditorState>
    val keyboardState: StateFlow<KeyboardState>

    val index: Int

    fun resetEditor(word: String)
    fun resetKeyboard(word: String, language: KeyboardLanguage)

    fun getReport() : EditorReport

    fun destroy()

}
