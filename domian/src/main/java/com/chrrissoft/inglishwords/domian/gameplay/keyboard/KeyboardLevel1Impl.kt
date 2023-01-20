package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.EnglishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.SpanishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Keyboard.KeyboardLevel1
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage.English
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage.Spanish
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardState.Companion.BREAKS_LIMIT
import com.chrrissoft.inglishwords.domian.gameplay.util.myPush
import com.chrrissoft.inglishwords.domian.gameplay.util.replace
import com.chrrissoft.inglishwords.domian.gameplay.util.stack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*


class KeyboardLevel1Impl(
    private val editor: Editor,
) : KeyboardLevel1 {

    private var word = ""

    private val _state = MutableStateFlow(KeyboardState())
    override val state = _state.asStateFlow()

    private inner class TextLimitedKeyImpl(
        override val text: String,
        override var stack: Stack<String>,
        override val coordinates: Coordinates,
    ) : Key.TextLimitedKey {
        override fun onClick() {
            if (stack.isEmpty()) return
            if (setText(text)) stack.pop()
        }

        fun copy(
            text: String = this.text,
            stack: Stack<String> = this.stack,
            coordinates: Coordinates = this.coordinates
        ) = TextLimitedKeyImpl(text, stack, coordinates)
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
                breaks = BREAKS_LIMIT,
                structure = buildStructure(language, word),
            )
        }
    }

    private fun deleteText() {
        val char = editor.deleteText() ?: return
        val key = findKey(char)
        val newKey = key.copy(stack = key.stack.myPush(char))
        val newStructure = _state.value.structure.replace(key, newKey)
        _state.update { it.copy(structure = newStructure) }
    }

    private fun thereAreLettersBreaks(block: (String) -> Unit) {
        val letter = editor.getNextCorrectLetter()
        if (_state.value.breaks != 0) block(letter)
    }

    private fun findKey(char: String): TextLimitedKeyImpl {
        val key = _state.value.structure.first {
            it as TextLimitedKeyImpl
            it.text == char
        }
        key as TextLimitedKeyImpl
        return key
    }

    private fun buildStructure(lang: KeyboardLanguage, word: String): List<Key> {
        return when (lang) {
            English -> buildEnglishStructure(word)
            Spanish -> buildSpanishStructure(word)
        }
    }

    private fun buildEnglishStructure(word: String): List<Key> {
        return buildList {
            EnglishStructure.coordinates.forEach { map ->
                if (word.contains(map.key)) {
                    val count = word.count { map.key == it.toString() }
                    val stack = stack(count) { map.key }
                    add(TextLimitedKeyImpl(map.key, stack, map.value))
                }
            }
        }.plus(buildOthersKeys())
    }

    private fun buildSpanishStructure(word: String): List<Key> {
        return buildList {
            SpanishStructure.coordinates.forEach { map ->
                if (word.contains(map.key)) {
                    val count = word.count { map.key == it.toString() }
                    val stack = stack(count) { map.key }
                    add(TextLimitedKeyImpl(map.key, stack, map.value))
                }
            }
        }.plus(buildOthersKeys())
    }

    private fun buildOthersKeys(): List<Key> {
        return buildList {
            add(DeleteKeyImpl(Coordinates.deleteCoordinates))
            add(MagicKeyImpl(Coordinates.magicCoordinates, BREAKS_LIMIT))
            add(SpacerKeyImpl(Coordinates.spacerCoordinates))
        }
    }

}
