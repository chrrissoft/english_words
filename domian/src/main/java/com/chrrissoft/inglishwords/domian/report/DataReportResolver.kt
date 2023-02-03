package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.WordResult.NativeWordResult
import com.chrrissoft.inglishwords.domian.report.WordResult.TargetWordResult

interface DataReportResolver<T: WordResult> {
    var time: Long
    var mistakes: Int
    var failures: Int

    fun sum(w: T) {
        time += w.time
        mistakes += w.mistakes
        failures += w.failures
    }

    data class TargetDataReportResolver(
        override var time: Long = 0,
        override var mistakes: Int = 0,
        override var failures: Int = 0
    ) : DataReportResolver<TargetWordResult>

    data class NativeDataReportResolver(
        override var time: Long = 0,
        override var mistakes: Int = 0,
        override var failures: Int = 0
    ) : DataReportResolver<NativeWordResult>

    data class TotalDataReportResolver(
        override var time: Long = 0,
        override var mistakes: Int = 0,
        override var failures: Int = 0
    ) : DataReportResolver<WordResult>
}