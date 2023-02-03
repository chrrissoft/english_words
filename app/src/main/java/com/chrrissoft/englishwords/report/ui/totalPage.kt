package com.chrrissoft.englishwords.report.ui

import androidx.compose.runtime.Composable
import com.chrrissoft.englishwords.report.ui.ReportPart.*
import com.chrrissoft.inglishwords.domian.report.Report.TotalReport

@Composable
internal fun TotalReportPage(
    report: TotalReport,
    reportPart: ReportPart
) {
    when(reportPart) {
        SESSION -> TotalSessionReport(report.reportStructure.session)
        WORDS -> TotalWordReport(report.reportStructure.words)
        LEVELS -> TotalLevelReport(report.reportStructure.levels)
        WORDS_BY_LEVELS -> TotalWordByLevelsReport(report.reportStructure.wordsByLevels)
    }
}