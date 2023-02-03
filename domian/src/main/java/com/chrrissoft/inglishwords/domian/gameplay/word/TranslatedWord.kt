package com.chrrissoft.inglishwords.domian.gameplay.word

sealed interface TranslatedWord {
    val text: String

    data class SpanishTranslatedWord(
        override val text: String
    ) : TranslatedWord

}
