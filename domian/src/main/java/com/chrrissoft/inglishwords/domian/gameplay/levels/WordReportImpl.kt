package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.report.WordReport

internal data class WordReportImpl(
    override val time: Long,
    override val index: Int,
    override val level: Int,
    override val text: String,
    override val failures: Int,
    override val mistakes: Int,
    override val language: WordReport.Language
) : WordReport {
    override fun sum(report: WordReport): WordReport {
        return WordReportImpl(
            time = report.time + this.time,
            index = this.index,
            level = this.level,
            text = this.text,
            failures = report.failures + this.failures,
            mistakes = report.mistakes + this.mistakes,
            language = this.language,
        )
    }
}
