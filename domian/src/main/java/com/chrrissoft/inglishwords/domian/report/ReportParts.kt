package com.chrrissoft.inglishwords.domian.report

import kotlin.time.Duration

interface ReportParts {
    val name: String
    val mistakes: Int
    val failures: Int
    val starts: Starts
    val time: Duration
}
