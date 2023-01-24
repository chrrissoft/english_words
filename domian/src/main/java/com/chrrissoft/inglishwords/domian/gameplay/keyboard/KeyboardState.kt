package com.chrrissoft.inglishwords.domian.gameplay.keyboard


data class KeyboardState(
    val structure: List<Key<*>> = emptyList(),
    val breaks: Int = BREAKS_LIMIT,
) {

    companion object {
        const val BREAKS_LIMIT = 3
    }

}
