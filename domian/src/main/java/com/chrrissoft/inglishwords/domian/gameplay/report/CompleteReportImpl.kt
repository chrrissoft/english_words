package com.chrrissoft.inglishwords.domian.gameplay.report

internal data class CompleteReportImpl(
    override val nativeReport: ReportStructure,
    override val targetReport: ReportStructure,
    override val promenadeReport: ReportStructure
) : CompleteReport
