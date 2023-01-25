package com.chrrissoft.inglishwords.domian.report

interface ReportStructure {
    val session: ReportParts
    val words: List<ReportParts>
    val levels: List<ReportParts>
    val levelsWords: List<List<ReportParts>>
}
