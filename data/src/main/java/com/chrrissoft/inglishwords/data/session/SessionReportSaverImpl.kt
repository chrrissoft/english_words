package com.chrrissoft.inglishwords.data.session

import com.chrrissoft.inglishwords.domian.report.Report
import com.chrrissoft.inglishwords.domian.report.SessionReportSaver
import kotlinx.coroutines.flow.update

class SessionReportSaverImpl : SessionReportSaver {

    override fun saveTarget(target: Report.TargetReport) {
        TARGET_REPORTS.update { target }
    }

    override fun saveNative(native: Report.NativeReport) {
        NATIVE_REPORTS.update { native }
    }

    override fun saveTotal(native: Report.TotalReport) {
        TOTAL_REPORTS.update { native }
    }

}
