package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.Starts.StartsImpl


interface ReportPart {
    val mistakes: Int
    val failures: Int
    val starts: Starts
    val time: Duration

    interface SessionReport : ReportPart {
        interface NativeSessionReport : SessionReport
        interface TargetSessionReport : SessionReport
        interface TotalSessionReport : SessionReport

        data class NativeSessionReportImpl(
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : NativeSessionReport

        data class TargetSessionReportImpl(
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : TargetSessionReport

        data class TotalSessionReportImpl(
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : TotalSessionReport
    }

    interface WordReport : ReportPart {
        val word: Word
        val translation: Translation
        interface NativeWordReport : WordReport
        interface TargetWordReport : WordReport
        interface TotalWordReport : WordReport

        data class NativeWordReportImpl(
            override val word: Word,
            override val translation: Translation,
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : NativeWordReport

        data class TargetWordReportImpl(
            override val word: Word,
            override val translation: Translation,
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : TargetWordReport

        data class TotalWordReportImpl(
            override val word: Word,
            override val translation: Translation,
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : TotalWordReport

        @JvmInline
        value class Translation(val value: String)

        @JvmInline
        value class Word(val value: String)
    }

    interface LevelReport : ReportPart {
        val number: Int
        interface NativeLevelReport : LevelReport
        interface TargetLevelReport : LevelReport
        interface TotalLevelReport : LevelReport

        data class NativeLevelReportImpl(
            override val number: Int,
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : NativeLevelReport

        data class TargetLevelReportImpl(
            override val number: Int,
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : TargetLevelReport

        data class TotalLevelReportImpl(
            override val number: Int,
            override val mistakes: Int,
            override val failures: Int,
            override val time: Duration,
            override val starts: Starts = StartsImpl(),
        ) : TotalLevelReport
    }

}
