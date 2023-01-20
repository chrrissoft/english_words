package com.chrrissoft.inglishwords.domian.gameplay.levels

interface LevelsSettings {

    val order: Order

    enum class Order {
        Interleaved, Random,
        TranslationFirst, TranslationLast
    }

}


