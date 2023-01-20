package com.chrrissoft.inglishwords.domian.gameplay.editor


data class EditorState(
    val mistakes: Int = 0,
    val userText: String = "",
    val failed: Boolean = false,
    val placeholder: String = "",
    val complete: Boolean = false,
) {
    fun reset(): EditorState {
        return EditorState()
    }
}
