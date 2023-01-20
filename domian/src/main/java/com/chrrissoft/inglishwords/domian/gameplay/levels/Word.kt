package com.chrrissoft.inglishwords.domian.gameplay.levels

internal sealed interface Word {
    val index: Int
    val text: String
    val lang: Language

    enum class Language {
        English, Spanish
    }

    data class TargetWord(
        override val index: Int,
        override val text: String,
        override val lang: Language,
    ) : Word

    data class TranslatedWord(
        override val index: Int,
        override val text: String,
        override val lang: Language,
    ) : Word
}
