package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.SpanishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Keyboard.KeyboardLevel3
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage.English
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage.Spanish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class KeyboardLevel3Impl(
    private val editor: Editor,
) : KeyboardLevel3 {

    private val _state =
        MutableStateFlow(KeyboardState(buildStructure(English)))
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
        _state.update {
            it.copy(
                breaks = KeyboardState.BREAKS_LIMIT,
                structure = buildStructure(language),
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

    private fun buildStructure(lang: KeyboardLanguage): List<Key> {
        return when (lang) {
            English -> buildEnglishStructure()
            Spanish -> buildSpanishStructure()
        }
    }

    private fun buildEnglishStructure(): List<Key> {
        return buildList {
            Coordinates.Companion.EnglishStructure.coordinates.forEach { map ->
                add(TextKeyImpl(map.key, map.value))
            }
        }.plus(buildOthersKeys())
    }

    private fun buildSpanishStructure(): List<Key> {
        return buildList {
            SpanishStructure.coordinates.forEach { map ->
                add(TextKeyImpl(map.key, map.value))
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
