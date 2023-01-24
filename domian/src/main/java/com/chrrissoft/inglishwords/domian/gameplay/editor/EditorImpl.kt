package com.chrrissoft.inglishwords.domian.gameplay.editor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class EditorImpl(
    private val placeholderEnabled: Boolean = false
) : Editor {

    private val _state = MutableStateFlow(EditorState())
    override val state = _state.asStateFlow()

    private var word = ""
    private var userText = ""

    init {
        setPlaceholder(word)
    }

    private fun setPlaceholder(word: String) {
        if (placeholderEnabled) {
            _state.update { it.copy(placeholder = word) }
        }
    }

    private var time: Long = System.currentTimeMillis()

    override fun setText(text: String): Boolean {
        userText += text
        isComplete(userText)
        val split = word.substring(userText.indices)
        if (userText != split) {
            updateFailure()
            isFailed()
            userText = userText.dropLast(1)
        } else updateUserText(userText)
        return userText == split
    }

    override fun getWord(): String {
        return word
    }

    override fun deleteText(): String? {
        if (_state.value.userText.isEmpty()) return null
        val char = _state.value.userText.last().toString()
        userText = userText.dropLast(1)
        _state.update { it.copy(userText = it.userText.dropLast(1)) }
        return char
    }

    override fun getNextCorrectLetter() : String {
        val position = userText.length
        if (position == word.length) return ""
        return this.word[position].toString()
    }

    override fun generateReport(): EditorReport {
        return EditorReportImpl(
            text = word,
            failed = _state.value.failed,
            mistakes = _state.value.mistakes,
            time = System.currentTimeMillis() - time,
        )
    }

    override fun reset(word: String) {
        userText = ""
        this.word = word
        _state.update { it.reset() }
        setPlaceholder(word)
    }

    /**
     * update the user text filed of the [state]
     * */
    private fun updateUserText(text: String) {
        _state.update { it.copy(userText = text) }
    }

    /**
     * update the [state] to complete
     * if the user text is equal than word
     * */
    private fun isComplete(text: String) {
        if (text == word) _state.update { it.copy(complete = true) }
    }

    /**
     * update the [state] to failed
     * if the setting limit is reached
     * */
    private fun isFailed() {
        if (_state.value.mistakes == 3) {
            _state.update { it.copy(failed = true) }
        }
    }

    /**
     * updates the [state] failure count
     * */
    private fun updateFailure() {
        _state.update {
            it.copy(mistakes = it.mistakes + 1)
        }
    }

}
