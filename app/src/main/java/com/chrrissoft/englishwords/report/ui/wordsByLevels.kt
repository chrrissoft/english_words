package com.chrrissoft.englishwords.report.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.inglishwords.domian.report.WordsByLevels
import com.chrrissoft.inglishwords.domian.report.WordsByLevels.*


@Composable
internal fun TargetWordByLevelsReport(
    report: List<TargetWordsByLevels>
) {
    WordByLevelsReport(report)
}

@Composable
internal fun NativeWordByLevelsReport(
    report: List<NativeWordsByLevels>
) {
    WordByLevelsReport(report)
}

@Composable
internal fun TotalWordByLevelsReport(
    report: List<TotalWordsByLevels>
) {
    WordByLevelsReport(report)
}


// TODO("implement the dropdown")
@Composable
private fun WordByLevelsReport(
    report: List<WordsByLevels>
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        report.forEach { wordsByLevels ->
            Text(text = "level ${wordsByLevels.level}")
            wordsByLevels.words.forEach {
                Spacer(Modifier.height(5.dp))
                ReportPartUi(it) {
                    Text(text = "word: ${it.word}")
                    Text(text = "translation: ${it.translation}")
                }
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}
