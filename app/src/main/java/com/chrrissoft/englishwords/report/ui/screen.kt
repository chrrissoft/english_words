package com.chrrissoft.englishwords.report.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.chrrissoft.englishwords.report.ui.ReportPart.*
import com.chrrissoft.englishwords.report.ui.ReportType.*
import com.chrrissoft.inglishwords.domian.report.Report
import com.chrrissoft.inglishwords.domian.report.Report.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    target: TargetReport,
    native: NativeReport,
    total: TotalReport,
) {

    var reportType by remember {
        mutableStateOf(TARGET)
    }

    var reportPart by remember {
        mutableStateOf(SESSION)
    }

    Scaffold(
        bottomBar = {
            ReportNavigationBar(reportType) {
                reportType = it
            }
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {

            when (reportType) {
                TARGET -> TargetReportPage(target, reportPart)
                NATIVE -> NativeReportPage(native, reportPart)
                TOTAL -> TotalReportPage(total, reportPart)
            }

            ChangeReportPartButtons(reportPart) {
                reportPart = it
            }
        }
    }
}

@Composable
fun ReportNavigationBar(
    reportType: ReportType,
    onReportPartChange: (ReportType) -> Unit
) {
    NavigationBar {
        ReportNavigationBarItem("TARGET", reportType == TARGET) {
            onReportPartChange(TARGET)
        }

        ReportNavigationBarItem("NATIVE", reportType == NATIVE) {
            onReportPartChange(NATIVE)
        }

        ReportNavigationBarItem("TOTAL", reportType == TOTAL) {
            onReportPartChange(TOTAL)
        }

    }
}

@Composable
fun RowScope.ReportNavigationBarItem(
    text: String,
    selected: Boolean,
    onReportPartChange: () -> Unit
) {
    NavigationBarItem(
        selected = selected,
        onClick = { onReportPartChange() },
        label = { Text(text) },
        icon = {
            Icon(imageVector = Rounded.Info, contentDescription = null)
        }
    )
}
