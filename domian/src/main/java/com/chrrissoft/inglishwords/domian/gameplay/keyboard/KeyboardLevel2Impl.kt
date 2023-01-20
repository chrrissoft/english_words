package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.EnglishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.SpanishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Keyboard.KeyboardLevel2
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage.English
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class KeyboardLevel2Impl(
    private val editor: Editor,
) : KeyboardLevel2 {

    private var word = ""

    private val _state = MutableStateFlow(KeyboardState())
    override val state = _state.asStateFlow()

    private inner class TextKeyImpl(
        override val text: String,
        override val coordinates: Coordinates,
    ) : Key.TextKey {
        override fun onClick() {
            setText(text)
        }
    }

    private inner class DeleteKeyImpl(
        override val coordinates: Coordinates
    ) : Key.DeleteKey {
        override fun onClick() {
            deleteText()
        }
    }

    private inner class SpacerKeyImpl(
        override val coordinates: Coordinates
    ) : Key.SpacerKey {
        override fun onClick() {}
    }

    private inner class MagicKeyImpl(
        override val coordinates: Coordinates,
        override var breaks: Int,
    ) : Key.MagicKey {
        override fun onClick() {
            thereAreLettersBreaks { letter ->
                findKey(letter).onClick()
                _state.update {
                    val decrement = it.breaks - 1
                    breaks = decrement
                    it.copy(breaks = decrement)
                }
            }
        }
    }

    private fun setText(text: String): Boolean {
        return editor.setText(text)
    }

    override fun reset(word: String, language: KeyboardLanguage) {
        this.word = word
        _state.update {
            it.copy(
                breaks = KeyboardState.BREAKS_LIMIT,
                structure = buildStructure(language, word),
            )
        }
    }

    private fun deleteText() {
        editor.deleteText()
    }

    private fun thereAreLettersBreaks(block: (String) -> Unit) {
        val letter = editor.getNextCorrectLetter()
        if (_state.value.breaks != 0) block(letter)
    }

    private fun findKey(char: String): TextKeyImpl {
        val key = _state.value.structure.first {
            it as TextKeyImpl
            it.text == char
        }
        key as TextKeyImpl
        return key
    }

    private fun buildStructure(lang: KeyboardLanguage, word: String): List<Key> {
        return when (lang) {
            English -> buildEnglishStructure(word)
            KeyboardLanguage.Spanish -> buildSpanishStructure(word)
        }
    }

    private fun buildEnglishStructure(word: String): List<Key> {
        return buildList {
            EnglishStructure.coordinates.forEach { map ->
                if (word.contains(map.key)) {
                    add(TextKeyImpl(map.key, map.value))
                }
            }
        }.plus(buildOthersKeys())
    }

    private fun buildSpanishStructure(word: String): List<Key> {
        return buildList {
            SpanishStructure.coordinates.forEach { map ->
                if (word.contains(map.key)) {
                    add(TextKeyImpl(map.key, map.value))
                }
            }
        }.plus(buildOthersKeys())
    }

    private fun buildOthersKeys(): List<Key> {
        return buildList {
            add(DeleteKeyImpl(Coordinates.deleteCoordinates))
            add(MagicKeyImpl(Coordinates.magicCoordinates, KeyboardState.BREAKS_LIMIT))
            add(SpacerKeyImpl(Coordinates.spacerCoordinates))
        }
    }

}
