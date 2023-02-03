package com.chrrissoft.englishwords.report.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.chrrissoft.englishwords.report.ui.ReportPart.*


@Composable
fun ChangeReportPartButtons(
    reportPart: ReportPart,
    onClick: (ReportPart) -> Unit,
) {
    Row {
        ChangeReportPartButton(
            text = "SESSION", selected = reportPart == SESSION,
        ) { onClick(SESSION) }
        ChangeReportPartButton(
            text = "LEVELS", selected = reportPart == LEVELS,
        ) { onClick(LEVELS) }
        ChangeReportPartButton(
            text = "WORDS", selected = reportPart == WORDS,
        ) { onClick(WORDS) }
        ChangeReportPartButton(
            text = "WORDS_BY_LEVELS", selected = reportPart == WORDS_BY_LEVELS,
        ) { onClick(WORDS_BY_LEVELS) }
    }
}

@Composable
fun ChangeReportPartButton(
    selected: Boolean,
    text: String, onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        colors = buttonColors(containerColor = if (selected) Color.Red else Color.Green)
    ) {
        Text(text = text)
    }
}
