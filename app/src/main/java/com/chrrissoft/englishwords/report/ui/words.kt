package com.chrrissoft.englishwords.report.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport
import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport.*


@Composable
internal fun TargetWordReport(
    report: List<TargetWordReport>
) {
    WordByLevelsReport(report)
}

@Composable
internal fun NativeWordReport(
    report: List<NativeWordReport>
) {
    WordByLevelsReport(report)
}

@Composable
internal fun TotalWordReport(
    report: List<TotalWordReport>
) {
    WordByLevelsReport(report)
}


// TODO("implement the dropdown")
@Composable
private fun WordByLevelsReport(
    report: List<WordReport>
) {
    Column {
        report.forEach {
            Spacer(Modifier.height(10.dp))
            ReportPartUi(it) {
                Text(text = "word: ${it.word}")
                Text(text = "translation: ${it.translation}")
            }
        }
    }
}
