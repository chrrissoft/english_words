package com.chrrissoft.inglishwords.domian.gameplay.report

import kotlin.time.Duration

internal data class ReportImpl(
    override val name: String,
    override val mistakes: Int,
    override val failures: Int,
    override val starts: Starts,
    override val time: Duration
) : Report
