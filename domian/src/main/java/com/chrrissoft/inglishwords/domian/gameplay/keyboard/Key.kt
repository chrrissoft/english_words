package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import java.util.*

sealed interface Key <T> {

    fun onClick() : T

    val coordinates: Coordinates

    interface DeleteKey : Key<Unit>
    interface SpacerKey : Key<Unit>

    interface MagicKey : Key<Unit> {
        val breaks: Int
    }

    interface TextKey : Key<Boolean> {
        val text: String
    }

    interface SelectableKey : Key<SelectedResult> {
        val text: String
        val selected: Boolean
        val selectable: Boolean
    }

    interface TextLimitedKey : Key<Boolean> {
        val text: String
        val stack: Stack<String>
    }

}

enum class SelectedResult {
    UNSELECT, SELECTED, NEXT_WORD
}