package com.chrrissoft.englishwords.report.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.chrrissoft.inglishwords.domian.report.ReportPart

enum class ReportType {
    TARGET, NATIVE, TOTAL,
}

enum class ReportPart {
    SESSION, WORDS, LEVELS, WORDS_BY_LEVELS
}

@Composable
fun ReportPartUi(
    report: ReportPart,
    block: @Composable () -> Unit
) {
    Column {
        block()
        Text(text = "failures: ${report.failures}")
        Text(text = "mistakes: ${report.mistakes}")
        Text(text = "time: ${report.time.minutes}: ${report.time.seconds}")
    }
}
