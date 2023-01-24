package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.EnglishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Coordinates.Companion.SpanishStructure
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Key.*
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Keyboard.KeyboardLevel5
import com.chrrissoft.inglishwords.domian.gameplay.util.modifiedElement
import com.chrrissoft.inglishwords.domian.gameplay.util.shuffled
import kotlinx.coroutines.flow.update


class KeyboardLevel5Impl(
    private val editor: Editor, player: GamePlayPlayer
) : KeyboardLevel5(editor, player) {

    inner class SelectableKeyImpl(
        override val text: String,
        override val selectable: Boolean,
        override val coordinates: Coordinates,
        override val selected: Boolean = false,
    ) : SelectableKey {
        override fun onClick(): SelectedResult {
            val isNext =
                editor.getNextCorrectLetter() == text
            return when (selectable) {
                true -> if (isNext) {
                    makeSelected()
                    setText(text)
                    SelectedResult.NEXT_WORD
                } else {
                    makeSelected()
                    SelectedResult.SELECTED
                }
                false -> {
                    setText(text)
                    SelectedResult.UNSELECT
                }
            }
        }

        private fun makeSelected() {
            mutableState.update { state ->
                state.copy(structure = state.structure.modifiedElement(this) {
                    it as SelectableKeyImpl
                    SelectableKeyImpl(it.text, it.selectable, it.coordinates, true)
                })
            }
        }
    }

    override fun deleteText() {
        editor.deleteText()
    }

    override fun findKey(char: String): SelectableKeyImpl {
        val key = mutableState.value.structure.first {
            it as SelectableKeyImpl
            it.text == char
        }
        key as SelectableKeyImpl
        return key
    }

    override fun buildEnglishStructure(word: String): List<Key<*>> {
        return buildList {
            EnglishStructure.coordinates.shuffled().forEach { map ->
                val selectable = word.contains(map.key)
                add(SelectableKeyImpl(map.key, selectable, map.value))
            }
        }.plus(buildOthersKeys())
    }

    override fun buildSpanishStructure(word: String): List<Key<*>> {
        return buildList {
            SpanishStructure.coordinates.shuffled().forEach { map ->
                val selectable = word.contains(map.key)
                add(SelectableKeyImpl(map.key, selectable, map.value))
            }
        }.plus(buildOthersKeys())
    }

}
