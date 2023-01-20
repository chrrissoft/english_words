package com.chrrissoft.inglishwords.domian.gameplay.word

sealed interface TranslatedWord {
    val text: String

    data class SpanishTranslatedWordImpl(
        override val text: String
    ) : TranslatedWord

}
