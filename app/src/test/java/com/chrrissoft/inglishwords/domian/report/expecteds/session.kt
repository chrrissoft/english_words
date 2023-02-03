package com.chrrissoft.inglishwords.domian.report.expecteds

import com.chrrissoft.inglishwords.domian.report.Duration.Companion.toDuration
import com.chrrissoft.inglishwords.domian.report.ReportPart.SessionReport.TargetSessionReportImpl
import com.chrrissoft.inglishwords.domian.report.ReportPart.SessionReport.NativeSessionReportImpl
import com.chrrissoft.inglishwords.domian.report.ReportPart.SessionReport.TotalSessionReportImpl

internal val targetSessionReportExpected = TargetSessionReportImpl(
    mistakes = 45, failures = 15, time = toDuration((10000L).times(10)),
)

internal val nativeSessionReportExpected = NativeSessionReportImpl(
    mistakes = 45, failures = 15, time = toDuration((10000L).times(10)),
)

internal val totalSessionReportExpected = TotalSessionReportImpl(
    mistakes = 90, failures = 30, time = toDuration((10000L).times(20)),
)
