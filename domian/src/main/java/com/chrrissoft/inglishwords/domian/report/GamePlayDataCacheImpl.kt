package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.ResultsByLevels.*
import com.chrrissoft.inglishwords.domian.report.WordResult.NativeWordResult
import com.chrrissoft.inglishwords.domian.report.WordResult.TargetWordResult

class GamePlayDataCacheImpl : GamePlayDataCache {

    private val rawStorage = mutableListOf<WordResult>()

    override fun forEachWord(block: (WordResult) -> Unit) {
        rawStorage.forEach(block)
    }

    override fun forEachTargetWord(block: (TargetWordResult) -> Unit) {
        rawStorage.filterIsInstance<TargetWordResult>().forEach(block)
    }

    override fun forEachNativeWord(block: (NativeWordResult) -> Unit) {
        rawStorage.filterIsInstance<NativeWordResult>().forEach(block)
    }

    /**
     * groups data into [WordResult] lists, where each item in the list is the result
     * of a single word.
     * */
    override fun forEachWords(block: (List<WordResult>) -> Unit) {
        rawStorage
            .groupBy { it.index }
            .forEach { block(it.value) }
    }

    override fun forEachTargetWords(block: (List<TargetWordResult>) -> Unit) {
        rawStorage.filterIsInstance<TargetWordResult>()
            .groupBy { it.index }
            .forEach { block(it.value) }
    }

    override fun forEachNativeWords(block: (List<NativeWordResult>) -> Unit) {
        rawStorage.filterIsInstance<NativeWordResult>()
            .groupBy { it.index }
            .forEach { block(it.value) }
    }

    /**
     * Levels
     * */
    override fun forEachLevel(block: (Int, List<WordResult>) -> Unit) {
        rawStorage.groupBy { it.level }
            .forEach { block(it.value.first().level, it.value) }
    }

    override fun forEachTargetLevel(block: (Int, List<TargetWordResult>) -> Unit) {
        rawStorage.filterIsInstance<TargetWordResult>()
            .groupBy { it.level }
            .forEach { block(it.value.first().level, it.value) }
    }

    override fun forEachNativeLevel(block: (Int, List<NativeWordResult>) -> Unit) {
        rawStorage.filterIsInstance<NativeWordResult>()
            .groupBy { it.level }
            .forEach { block(it.value.first().level, it.value) }
    }


    /**
     * words by level
     * */
    override fun forEachWordsByLevel(block: (ResultsByLevels) -> Unit) {
        forEachLevel { level, results ->
            block(ResultsByLevelsImpl(level, results))
        }
    }

    override fun forEachTargetWordsByLevel(block: (TargetResultsByLevels) -> Unit) {
        forEachTargetLevel { level, results ->
            block(TargetResultsByLevelsImpl(level, results))
        }
    }

    override fun forEachNativeWordsByLevel(block: (NativeResultsByLevels) -> Unit) {
        forEachNativeLevel { level, results ->
            block(NativeResultsByLevelsImpl(level, results))
        }
    }

    override fun save(result: WordResult) {
        rawStorage.add(result)
        rawStorage.sortBy { it.index }
    }

}
