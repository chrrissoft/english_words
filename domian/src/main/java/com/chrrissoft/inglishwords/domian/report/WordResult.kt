package com.chrrissoft.inglishwords.domian.report


/**
 * interface use to define the data to [SessionReporter] for generate report
 * this data will be sending in each word of each [com.chrrissoft.inglishwords.domian.gameplay.levels.Level]
 *
 * @property time the total time it took the user to solve the word (time of failures will be added)
 * @property index the index of the word, used to sort the results based on the input from
 * [com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsManagerImpl]
 * @property targetText the text of the word
 * @property level the level name of the [com.chrrissoft.inglishwords.domian.gameplay.levels.Level]
 * @property failures the number of failures in attempts to solve the word
 * @property mistakes the number of errors on solve the word
 * */
sealed interface WordResult {
    val time: Long
    val index: Int
    val level: Int
    val targetText: TargetText
    val nativeText: NativeText
    val failures: Int
    val mistakes: Int

    interface TargetWordResult : WordResult {
        override fun sum(report: WordResult): TargetWordResult
    }
    interface NativeWordResult : WordResult {
        override fun sum(report: WordResult): NativeWordResult
    }

    @JvmInline
    value class TargetText(val text: String)

    @JvmInline
    value class NativeText(val text: String)

    fun sum(report: WordResult) : WordResult
}
