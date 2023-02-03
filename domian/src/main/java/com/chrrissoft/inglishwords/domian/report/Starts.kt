package com.chrrissoft.inglishwords.domian.report

interface Starts {
    private companion object {
        private const val MAX_NUMBER = 5
    }
    val stars: List<Int?>

    data class StartsImpl(
        override val stars: List<Int?> = emptyList(),
    ): Starts
}
