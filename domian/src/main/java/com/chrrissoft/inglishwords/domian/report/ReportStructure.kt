package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.ReportPart.*
import com.chrrissoft.inglishwords.domian.report.ReportPart.LevelReport.*
import com.chrrissoft.inglishwords.domian.report.ReportPart.SessionReport.*
import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport.*
import com.chrrissoft.inglishwords.domian.report.WordsByLevels.*

interface ReportStructure {

    val session: SessionReport
    val words: List<WordReport>
    val levels: List<LevelReport>
    val wordsByLevels: List<WordsByLevels>

    interface TargetReportStructure : ReportStructure {
        override val session: TargetSessionReport
        override val words: List<TargetWordReport>
        override val levels: List<TargetLevelReport>
        override val wordsByLevels: List<TargetWordsByLevels>
    }

    interface NativeReportStructure : ReportStructure {
        override val session: NativeSessionReport
        override val words: List<NativeWordReport>
        override val levels: List<NativeLevelReport>
        override val wordsByLevels: List<NativeWordsByLevels>
    }

    interface TotalReportStructure : ReportStructure {
        override val session: TotalSessionReport
        override val words: List<TotalWordReport>
        override val levels: List<TotalLevelReport>
        override val wordsByLevels: List<TotalWordsByLevels>
    }

    data class TargetReportStructureImpl(
        override val session: TargetSessionReport,
        override val words: List<TargetWordReport>,
        override val levels: List<TargetLevelReport>,
        override val wordsByLevels: List<TargetWordsByLevels>,
    ) : TargetReportStructure

    data class NativeReportStructureImpl(
        override val session: NativeSessionReport,
        override val words: List<NativeWordReport>,
        override val levels: List<NativeLevelReport>,
        override val wordsByLevels: List<NativeWordsByLevels>,
    ) : NativeReportStructure

    data class TotalReportStructureImpl(
        override val session: TotalSessionReport,
        override val words: List<TotalWordReport>,
        override val levels: List<TotalLevelReport>,
        override val wordsByLevels: List<TotalWordsByLevels>,
    ) : TotalReportStructure

}
