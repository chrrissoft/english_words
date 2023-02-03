package com.chrrissoft.inglishwords.domian.gameplay.levels

internal sealed interface Word {
    val index: Int
    val text: String
    val translation: String
    val lang: Language

    enum class Language {
        English, Spanish
    }

    data class TargetWord(
        override val index: Int,
        override val text: String,
        override val lang: Language,
        override val translation: String,
    ) : Word

    data class NativeWord(
        override val index: Int,
        override val text: String,
        override val lang: Language,
        override val translation: String,
    ) : Word
}
