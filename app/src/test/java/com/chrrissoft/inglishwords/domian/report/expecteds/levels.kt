package com.chrrissoft.inglishwords.domian.report.expecteds

import com.chrrissoft.inglishwords.domian.report.Duration.Companion.toDuration
import com.chrrissoft.inglishwords.domian.report.ReportPart.LevelReport.TargetLevelReportImpl
import com.chrrissoft.inglishwords.domian.report.ReportPart.LevelReport.NativeLevelReportImpl
import com.chrrissoft.inglishwords.domian.report.ReportPart.LevelReport.TotalLevelReportImpl

internal val targetLevelReportReportExpected = listOf(
    TargetLevelReportImpl(number = 1, failures = 3, mistakes = 9, time = toDuration((20000L))),
    TargetLevelReportImpl(number = 2, failures = 3, mistakes = 9, time = toDuration((20000L))),
    TargetLevelReportImpl(number = 3, failures = 3, mistakes = 9, time = toDuration((20000L))),
    TargetLevelReportImpl(number = 4, failures = 3, mistakes = 9, time = toDuration((20000L))),
    TargetLevelReportImpl(number = 5, failures = 3, mistakes = 9, time = toDuration((20000L))),
)

internal val nativeLevelReportReportExpected = listOf(
    NativeLevelReportImpl(number = 1, failures = 3, mistakes = 9, time = toDuration((20000L))),
    NativeLevelReportImpl(number = 2, failures = 3, mistakes = 9, time = toDuration((20000L))),
    NativeLevelReportImpl(number = 3, failures = 3, mistakes = 9, time = toDuration((20000L))),
    NativeLevelReportImpl(number = 4, failures = 3, mistakes = 9, time = toDuration((20000L))),
    NativeLevelReportImpl(number = 5, failures = 3, mistakes = 9, time = toDuration((20000L))),
)

internal val totalLevelReportReportExpected = listOf(
    TotalLevelReportImpl(number = 1, failures = 6, mistakes = 18, time = toDuration((40000L))),
    TotalLevelReportImpl(number = 2, failures = 6, mistakes = 18, time = toDuration((40000L))),
    TotalLevelReportImpl(number = 3, failures = 6, mistakes = 18, time = toDuration((40000L))),
    TotalLevelReportImpl(number = 4, failures = 6, mistakes = 18, time = toDuration((40000L))),
    TotalLevelReportImpl(number = 5, failures = 6, mistakes = 18, time = toDuration((40000L))),
)
