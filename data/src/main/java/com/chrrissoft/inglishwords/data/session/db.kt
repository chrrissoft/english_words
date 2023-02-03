package com.chrrissoft.inglishwords.data.session

import com.chrrissoft.inglishwords.domian.report.Report
import kotlinx.coroutines.flow.MutableStateFlow

val TARGET_REPORTS = MutableStateFlow<Report.TargetReport?>(null)
val NATIVE_REPORTS = MutableStateFlow<Report.NativeReport?>(null)
val TOTAL_REPORTS = MutableStateFlow<Report.TotalReport?>(null)
