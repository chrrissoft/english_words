package com.chrrissoft.inglishwords.domian.gameplay.report

import kotlin.time.Duration

interface Report {
    val name: String
    val mistakes: Int
    val failures: Int
    val starts: Starts
    val time: Duration
}
