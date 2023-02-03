package com.chrrissoft.englishwords.report.ui

import androidx.compose.runtime.Composable
import com.chrrissoft.englishwords.report.ui.ReportPart.*
import com.chrrissoft.inglishwords.domian.report.Report.TargetReport

@Composable
internal fun TargetReportPage(
    report: TargetReport,
    reportPart: ReportPart
) {
    when(reportPart) {
        SESSION -> TargetSessionReport(report.reportStructure.session)
        WORDS -> TargetWordReport(report.reportStructure.words)
        LEVELS -> TargetLevelReport(report.reportStructure.levels)
        WORDS_BY_LEVELS -> TargetWordByLevelsReport(report.reportStructure.wordsByLevels)
    }
}
