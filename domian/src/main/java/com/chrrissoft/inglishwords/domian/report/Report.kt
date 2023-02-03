package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.ReportStructure.*


interface Report {

    val reportStructure: ReportStructure

    interface TargetReport : Report {
        override val reportStructure: TargetReportStructure
    }

    interface NativeReport : Report {
        override val reportStructure: NativeReportStructure
    }

    interface TotalReport : Report {
        override val reportStructure: TotalReportStructure
    }

    data class TargetReportIml(
        override val reportStructure: TargetReportStructure,
    ) : TargetReport

    data class NativeReportIml(
        override val reportStructure: NativeReportStructure,
    ) : NativeReport

    data class TotalReportIml(
        override val reportStructure: TotalReportStructure,
    ) : TotalReport

}
