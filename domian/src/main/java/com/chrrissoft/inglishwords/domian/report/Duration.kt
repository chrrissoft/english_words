package com.chrrissoft.inglishwords.domian.report

import kotlin.time.DurationUnit.MILLISECONDS
import kotlin.time.toDuration

interface Duration {
    val seconds: Long
    val minutes: Long

    companion object {
        fun toDuration(time: Long): Duration {
            val seconds = time.div(1000)
            val minutes = time.toDuration(MILLISECONDS).inWholeMinutes
            return DurationImpl(seconds, minutes)
        }
    }

    data class DurationImpl(
        override val seconds: Long,
        override val minutes: Long,
    ) : Duration

}
