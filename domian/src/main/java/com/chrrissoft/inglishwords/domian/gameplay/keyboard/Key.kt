package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import java.util.*

sealed interface Key {

    fun onClick()

    val coordinates: Coordinates

    interface DeleteKey : Key
    interface SpacerKey : Key

    interface MagicKey : Key {
        val breaks: Int
    }
    interface TextKey : Key {
        val text: String
    }
    interface TextLimitedKey : Key {
        val text: String
        val stack: Stack<String>
    }

}