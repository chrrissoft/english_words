package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.WordResult.NativeWordResult
import com.chrrissoft.inglishwords.domian.report.WordResult.TargetWordResult

interface ResultsByLevels  {
    val level: Int
    val words: List<WordResult>

    interface TargetResultsByLevels : ResultsByLevels {
        override val level: Int
        override val words: List<TargetWordResult>
    }

    interface NativeResultsByLevels : ResultsByLevels {
        override val level: Int
        override val words: List<NativeWordResult>
    }

    data class TargetResultsByLevelsImpl(
        override val level: Int,
        override val words: List<TargetWordResult>
    ) : TargetResultsByLevels

    data class NativeResultsByLevelsImpl(
        override val level: Int,
        override val words: List<NativeWordResult>
    ) : NativeResultsByLevels

    data class ResultsByLevelsImpl(
        override val level: Int,
        override val words: List<WordResult>
    ) : ResultsByLevels

}
