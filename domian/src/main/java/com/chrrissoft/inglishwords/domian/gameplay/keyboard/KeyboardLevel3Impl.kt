package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.SpanishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Key.TextKey
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Keyboard.KeyboardLevel3


class KeyboardLevel3Impl(
    private val editor: Editor, player: GamePlayPlayer
) : KeyboardLevel3(editor, player) {


    inner class TextKeyImpl(
        override val text: String,
        override val coordinates: Coordinates,
    ) : TextKey {
        override fun onClick() = setText(text)
    }

    override fun deleteText() {
        editor.deleteText()
    }

    override fun findKey(char: String): TextKeyImpl {
        val key = mutableState.value.structure.first {
            it as TextKeyImpl
            it.text == char
        }
        key as TextKeyImpl
        return key
    }

    override fun buildEnglishStructure(word: String): List<Key<*>> {
        return buildList {
            Coordinates.Companion.EnglishStructure.coordinates.forEach { map ->
                add(TextKeyImpl(map.key, map.value))
            }
        }.plus(buildOthersKeys())
    }

    override fun buildSpanishStructure(word: String): List<Key<*>> {
        return buildList {
            SpanishStructure.coordinates.forEach { map ->
                add(TextKeyImpl(map.key, map.value))
            }
        }.plus(buildOthersKeys())
    }


}
