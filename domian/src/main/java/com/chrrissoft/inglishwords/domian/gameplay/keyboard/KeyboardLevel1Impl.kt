package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.EnglishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.SpanishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Key.TextLimitedKey
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Keyboard.KeyboardLevel1
import com.chrrissoft.inglishwords.domian.gameplay.util.myPush
import com.chrrissoft.inglishwords.domian.gameplay.util.replace
import com.chrrissoft.inglishwords.domian.gameplay.util.stack
import kotlinx.coroutines.flow.update
import java.util.*


class KeyboardLevel1Impl(
    private val editor: Editor, player: GamePlayPlayer
) : KeyboardLevel1(editor, player) {

    inner class TextLimitedKeyImpl(
        override val text: String,
        override val stack: Stack<String>,
        override val coordinates: Coordinates,
    ) : TextLimitedKey {
        override fun onClick(): Boolean {
            if (stack.isEmpty()) return false
            return if (setText(text)) {
                stack.pop()
                true
            } else false
        }

        fun copy(
            text: String = this.text,
            stack: Stack<String> = this.stack,
            coordinates: Coordinates = this.coordinates
        ) = TextLimitedKeyImpl(text, stack, coordinates)
    }

    override fun deleteText() {
        val char = editor.deleteText() ?: return
        val key = findKey(char)
        val newKey = key.copy(stack = key.stack.myPush(char))
        val newStructure = mutableState.value.structure.replace(key, newKey)
        mutableState.update { it.copy(structure = newStructure) }
    }

    override fun findKey(char: String): TextLimitedKeyImpl {
        val key = mutableState.value.structure.first {
            it as TextLimitedKeyImpl
            it.text == char
        }
        key as TextLimitedKeyImpl
        return key
    }

    override fun buildEnglishStructure(word: String): List<Key<*>> {
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

    override fun buildSpanishStructure(word: String): List<Key<*>> {
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

}
