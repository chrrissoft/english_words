package com.chrrissoft.inglishwords.domian.gameplay.word

sealed interface Word {
    val text: String
    val translation: TranslatedWord

    data class EnglishWord(
        override val text: String,
        override val translation: TranslatedWord
    ) : Word

}
