package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport
import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport.*

interface WordsByLevels {
    val level: Int
    val words: List<WordReport>

    interface TargetWordsByLevels : WordsByLevels {
        override val level: Int
        override val words: List<TargetWordReport>
    }

    interface NativeWordsByLevels : WordsByLevels {
        override val level: Int
        override val words: List<NativeWordReport>
    }

    interface TotalWordsByLevels : WordsByLevels {
        override val level: Int
        override val words: List<TotalWordReport>
    }


    data class TargetWordsByLevelsImpl(
        override val level: Int,
        override val words: List<TargetWordReport>
    ) : TargetWordsByLevels

    data class NativeWordsByLevelsImpl(
        override val level: Int,
        override val words: List<NativeWordReport>
    ) : NativeWordsByLevels

    data class TotalWordsByLevelsImpl(
        override val level: Int,
        override val words: List<TotalWordReport>
    ) : TotalWordsByLevels
}
