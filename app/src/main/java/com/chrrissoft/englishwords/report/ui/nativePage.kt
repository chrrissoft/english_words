package com.chrrissoft.englishwords.report.ui

import androidx.compose.runtime.Composable
import com.chrrissoft.englishwords.report.ui.ReportPart.*
import com.chrrissoft.inglishwords.domian.report.Report.NativeReport

@Composable
internal fun NativeReportPage(
    report: NativeReport,
    reportPart: ReportPart
) {
    when(reportPart) {
        SESSION -> NativeSessionReport(report.reportStructure.session)
        WORDS -> NativeWordReport(report.reportStructure.words)
        LEVELS -> NativeLevelReport(report.reportStructure.levels)
        WORDS_BY_LEVELS -> NativeWordByLevelsReport(report.reportStructure.wordsByLevels)
    }
}