package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import kotlinx.coroutines.flow.StateFlow

sealed interface Keyboard {

    interface KeyboardLevel1 : Keyboard
    interface KeyboardLevel2 : Keyboard
    interface KeyboardLevel3 : Keyboard
    interface KeyboardLevel4 : Keyboard
    interface KeyboardLevel5 : Keyboard

    val state: StateFlow<KeyboardState>

    fun reset(word: String, language: KeyboardLanguage)

}



















