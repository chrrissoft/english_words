package com.chrrissoft.inglishwords.domian.gameplay.editor

import kotlinx.coroutines.flow.StateFlow


interface Editor {

    val state: StateFlow<EditorState>

    /**
     * height oder function than is used for the keyboard
     * tell with this editor. used to set text on editor state
     * @param text text to set on editor state
     * @return true if the text is correct
     * */
    fun setText(text: String): Boolean

    /**
     * height oder function than is used for the keyboard
     * tell with this editor. is used to delete the last
     * letter on the text editor state.
     * @return the character than is deleted
     * */
    fun deleteText(): String?

    /**
     * used from keyboard to get the next correct letter and send such letter
     * @return the next correct letter of the word
     * */
    fun getNextCorrectLetter(): String

    /**
     * an inform generated for this editor that represents
     * the resulting data from the editor session
     * @return [EditorReport]
     * */
    fun generateReport(): EditorReport

    /**
     * called when the [EditorState.failed] or [EditorState.complete] is true
     * this will reset the editor state and all them properties
     * @param word the word to define on the editor
     * */
    fun reset(word: String)

}
