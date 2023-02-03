package com.chrrissoft.englishwords.report.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.chrrissoft.inglishwords.domian.report.ReportPart.SessionReport
import com.chrrissoft.inglishwords.domian.report.ReportPart.SessionReport.*

@Composable
internal fun TargetSessionReport(
    report: TargetSessionReport
) {
    SessionReport(report)
}

@Composable
internal fun NativeSessionReport(
    report: NativeSessionReport
) {
    SessionReport(report)
}

@Composable
internal fun TotalSessionReport(
    report: TotalSessionReport
) {
    SessionReport(report)
}

@Composable
private fun SessionReport(
    report: SessionReport
) {
    ReportPartUi(report) {}
}
