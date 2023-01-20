package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorReport
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Keyboard
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LevelImp(
    override val index: Int,
    private val editor: Editor,
    private val keyboard: Keyboard,
) : Level {

    private val scope = MainScope()

    private val _editorState = MutableStateFlow(editor.state.value)
    private val _keyboardState = MutableStateFlow(keyboard.state.value)

    override val editorState = _editorState.asStateFlow()
    override val keyboardState = _keyboardState.asStateFlow()

    init {
        updateEditorState()
        updateKeyboardState()
    }

    override fun resetEditor(word: String) {
        editor.reset(word)
    }

    override fun resetKeyboard(word: String, language: KeyboardLanguage) {
        keyboard.reset(word, language)
    }

    override fun getReport(): EditorReport {
        return editor.generateReport()
    }

    override fun destroy() {
        scope.cancel()
    }

    private fun updateEditorState() {
        scope.launch {
            editor.state.collect { state ->
                _editorState.update { state }
            }
        }
    }

    private fun updateKeyboardState() {
        scope.launch {
            keyboard.state.collect { state ->
                _keyboardState.update { state }
            }
        }
    }

}
