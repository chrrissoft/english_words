package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.domian.report.DataReportResolver.*
import com.chrrissoft.inglishwords.domian.report.Duration.Companion.toDuration
import com.chrrissoft.inglishwords.domian.report.Report.*
import com.chrrissoft.inglishwords.domian.report.ReportPart.LevelReport.*
import com.chrrissoft.inglishwords.domian.report.ReportPart.SessionReport.*
import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport.*
import com.chrrissoft.inglishwords.domian.report.ReportStructure.*
import com.chrrissoft.inglishwords.domian.report.WordResult.NativeWordResult
import com.chrrissoft.inglishwords.domian.report.WordResult.TargetWordResult
import com.chrrissoft.inglishwords.domian.report.WordsByLevels.*

class SessionReporterImpl(
    private val cache: GamePlayDataCache,
    private val saver: SessionReportSaver,
) : SessionReporter {

    override fun save() {
        saver.saveTarget(buildTargetReport())
        saver.saveNative(buildNativeReport())
        saver.saveTotal(buildTotalReport())
    }

    override fun addWordReport(data: WordResult) {
        cache.save(data)
    }

    /**
     * report
     * */
    private fun buildTargetReport(): TargetReport {
        return TargetReportIml(buildTargetReportStructure())
    }

    private fun buildNativeReport(): NativeReport {
        return NativeReportIml(buildNativeReportStructure())
    }

    private fun buildTotalReport(): TotalReport {
        return TotalReportIml(buildTotalReportStructure())
    }


    /**
     * structures
     * */
    private fun buildTargetReportStructure(): TargetReportStructure {
        return TargetReportStructureImpl(
            session = buildTargetSession(),
            words = buildTargetWords(),
            levels = buildTargetLevels(),
            wordsByLevels = buildTargetWordsByLevels()
        )
    }

    private fun buildNativeReportStructure(): NativeReportStructure {
        return NativeReportStructureImpl(
            words = buildNativeWords(),
            session = buildNativeSession(),
            levels = buildNativeLevels(),
            wordsByLevels = buildNativeWordsByLevels()
        )
    }

    private fun buildTotalReportStructure(): TotalReportStructure {
        return TotalReportStructureImpl(
            words = buildTotalWords(),
            session = buildTotalSession(),
            levels = buildTotalLevels(),
            wordsByLevels = buildTotalWordsByLevels()
        )
    }


    /**
     * session reports
     **/
    private fun buildTargetSession(): TargetSessionReport {
        val data = TargetDataReportResolver()
        cache.forEachTargetWord { data.sum(it) }
        return TargetSessionReportImpl(data.mistakes, data.failures, toDuration(data.time))
    }

    private fun buildNativeSession(): NativeSessionReport {
        val data = NativeDataReportResolver()
        cache.forEachNativeWord { data.sum(it) }
        return NativeSessionReportImpl(data.mistakes, data.failures, toDuration(data.time))
    }

    private fun buildTotalSession(): TotalSessionReport {
        val data = TotalDataReportResolver()
        cache.forEachWord { data.sum(it) }
        return TotalSessionReportImpl(data.mistakes, data.failures, toDuration(data.time))
    }


    /**
     * words reports
     **/
    private fun buildTargetWords(): List<TargetWordReport> {
        val list = mutableListOf<TargetWordReport>()
        cache.forEachTargetWords { results ->
            val data = TargetDataReportResolver()
            val word = Word(results.first().targetText.text)
            val translation = Translation(results.first().nativeText.text)
            results.forEach { data.sum(it) }
            list.add(
                TargetWordReportImpl(
                    word, translation, data.mistakes, data.failures, toDuration(data.time)
                )
            )
        }
        return list
    }

    private fun buildNativeWords(): List<NativeWordReport> {
        val list = mutableListOf<NativeWordReport>()
        cache.forEachNativeWords { results ->
            val data = NativeDataReportResolver()
            val word = Word(results.first().nativeText.text)
            val translation = Translation(results.first().targetText.text)
            results.forEach { data.sum(it) }
            list.add(
                NativeWordReportImpl(
                    word, translation, data.mistakes, data.failures, toDuration(data.time)
                )
            )
        }
        return list
    }

    private fun buildTotalWords(): List<TotalWordReport> {
        val list = mutableListOf<TotalWordReport>()
        cache.forEachWords { results ->
            val data = TotalDataReportResolver()
            val word = Word(results.first { it is TargetWordResult }.targetText.text)
            val translation = Translation(results.first { it is NativeWordResult }.nativeText.text)
            results.forEach { data.sum(it) }
            list.add(
                TotalWordReportImpl(
                    word, translation, data.mistakes,
                    data.failures, toDuration(data.time)
                )
            )
        }
        return list
    }


    /**
     * levels reports
     **/
    private fun buildTargetLevels(): List<TargetLevelReport> {
        val list = mutableListOf<TargetLevelReport>()
        cache.forEachTargetLevel { i, results ->
            val data = TargetDataReportResolver()
            results.forEach { data.sum(it) }
            list.add(TargetLevelReportImpl(i, data.mistakes, data.failures, toDuration(data.time)))
        }
        return list
    }

    private fun buildNativeLevels(): List<NativeLevelReport> {
        val list = mutableListOf<NativeLevelReport>()
        cache.forEachNativeLevel { i, results ->
            val data = NativeDataReportResolver()
            results.forEach { data.sum(it) }
            list.add(NativeLevelReportImpl(i, data.mistakes, data.failures, toDuration(data.time)))
        }
        return list
    }

    private fun buildTotalLevels(): List<TotalLevelReport> {
        val list = mutableListOf<TotalLevelReport>()
        cache.forEachLevel { i, results ->
            val data = TotalDataReportResolver()
            results.forEach { data.sum(it) }
            list.add(TotalLevelReportImpl(i, data.mistakes, data.failures, toDuration(data.time)))
        }
        return list
    }

    /**
     * words by levels
     * */
    private fun buildTargetWordsByLevels(): List<TargetWordsByLevels> {
        val result = mutableListOf<TargetWordsByLevels>()
        cache.forEachTargetWordsByLevel { resultsByLevel ->
            val list = mutableListOf<TargetWordReport>()
            resultsByLevel.words.forEach {
                list.add(
                    TargetWordReportImpl(
                        word = Word(it.targetText.text),
                        mistakes = it.mistakes,
                        failures = it.failures,
                        time = toDuration(it.time),
                        translation = Translation(it.nativeText.text),
                    )
                )
            }
            result.add(TargetWordsByLevelsImpl(resultsByLevel.level, list.toList()))
            list.clear()
        }

        return result
    }

    private fun buildNativeWordsByLevels(): List<NativeWordsByLevels> {
        val result = mutableListOf<NativeWordsByLevels>()
        cache.forEachNativeWordsByLevel { resultsByLevel ->
            val list = mutableListOf<NativeWordReport>()
            resultsByLevel.words.forEach {
                list.add(
                    NativeWordReportImpl(
                        word = Word(it.nativeText.text),
                        mistakes = it.mistakes,
                        failures = it.failures,
                        time = toDuration(it.time),
                        translation = Translation(it.targetText.text),
                    )
                )
            }
            result.add(NativeWordsByLevelsImpl(resultsByLevel.level, list.toList()))
            list.clear()
        }

        return result
    }

    private fun buildTotalWordsByLevels(): List<TotalWordsByLevels> {
        val result = mutableListOf<TotalWordsByLevels>()
        cache.forEachWordsByLevel { resultsByLevel ->
            val list = mutableListOf<TotalWordReport>()
            val natives = resultsByLevel.words.filterIsInstance<NativeWordResult>()
            val targets = resultsByLevel.words.filterIsInstance<TargetWordResult>()
            targets.forEach { targetWord ->
                val native = natives.first { targetWord.index == it.index }
                list.add(
                    TotalWordReportImpl(
                        word = Word(native.targetText.text),
                        mistakes = native.mistakes.plus(targetWord.mistakes),
                        failures = native.failures.plus(targetWord.failures),
                        time = toDuration(native.time.plus(targetWord.time)),
                        translation = Translation(native.nativeText.text),
                    )
                )
            }

            result.add(TotalWordsByLevelsImpl(resultsByLevel.level, list.toList()))
            list.clear()
        }

        return result
    }

}
