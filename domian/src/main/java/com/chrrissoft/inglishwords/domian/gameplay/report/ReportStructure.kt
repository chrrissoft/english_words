package com.chrrissoft.inglishwords.domian.gameplay.report

data class ReportStructure(
    val session: Report,
    val words: List<Report>,
    val levels: List<Report>,
    val levelsWords: List<List<Report>>,
)
