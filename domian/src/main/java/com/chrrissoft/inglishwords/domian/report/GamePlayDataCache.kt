package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.ResultsByLevels.NativeResultsByLevels
import com.chrrissoft.inglishwords.domian.report.ResultsByLevels.TargetResultsByLevels
import com.chrrissoft.inglishwords.domian.report.WordResult.NativeWordResult
import com.chrrissoft.inglishwords.domian.report.WordResult.TargetWordResult

interface GamePlayDataCache {

    fun forEachWord(block: (WordResult) -> Unit)
    fun forEachTargetWord(block: (TargetWordResult) -> Unit)
    fun forEachNativeWord(block: (NativeWordResult) -> Unit)

    fun forEachWords(block: (List<WordResult>) -> Unit)
    fun forEachTargetWords(block: (List<TargetWordResult>) -> Unit)
    fun forEachNativeWords(block: (List<NativeWordResult>) -> Unit)

    fun forEachLevel(block: (Int, List<WordResult>) -> Unit)
    fun forEachTargetLevel(block: (Int, List<TargetWordResult>) -> Unit)
    fun forEachNativeLevel(block: (Int, List<NativeWordResult>) -> Unit)

    fun forEachWordsByLevel(block: (ResultsByLevels) -> Unit)
    fun forEachTargetWordsByLevel(block: (TargetResultsByLevels) -> Unit)
    fun forEachNativeWordsByLevel(block: (NativeResultsByLevels) -> Unit)

    fun save(result: WordResult)

}
