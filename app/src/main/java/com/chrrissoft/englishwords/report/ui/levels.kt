package com.chrrissoft.englishwords.report.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.inglishwords.domian.report.ReportPart.LevelReport
import com.chrrissoft.inglishwords.domian.report.ReportPart.LevelReport.*


@Composable
internal fun TargetLevelReport(
    report: List<TargetLevelReport>
) {
    LevelReport(report)
}

@Composable
internal fun NativeLevelReport(
    report: List<NativeLevelReport>
) {
    LevelReport(report)
}

@Composable
internal fun TotalLevelReport(
    report: List<TotalLevelReport>
) {
    LevelReport(report)
}


// TODO("implement the dropdown")
@Composable
private fun LevelReport(
    report: List<LevelReport>
) {
    Column {
        report.forEach {
            Spacer(Modifier.height(10.dp))
            ReportPartUi(it) {
                Text(text = "number: ${it.number}")
            }
        }
    }
}
