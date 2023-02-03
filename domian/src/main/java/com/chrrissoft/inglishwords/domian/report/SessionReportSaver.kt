package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.Report.*

interface SessionReportSaver {
    fun saveTarget(target: TargetReport)

    fun saveNative(native: NativeReport)

    fun saveTotal(native: TotalReport)
}
