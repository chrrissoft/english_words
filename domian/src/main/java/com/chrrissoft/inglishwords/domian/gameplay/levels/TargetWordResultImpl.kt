package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.report.WordResult
import com.chrrissoft.inglishwords.domian.report.WordResult.*

data class TargetWordResultImpl(
    override val time: Long,
    override val index: Int,
    override val level: Int,
    override val targetText: TargetText,
    override val failures: Int,
    override val mistakes: Int,
    override val nativeText: NativeText,
) : TargetWordResult {
    override fun sum(report: WordResult): TargetWordResult {
        return TargetWordResultImpl(
            targetText = this.targetText,
            index = this.index,
            level = this.level,
            time = report.time + this.time,
            nativeText = this.nativeText,
            failures = report.failures + this.failures,
            mistakes = report.mistakes + this.mistakes,
        )
    }
}
