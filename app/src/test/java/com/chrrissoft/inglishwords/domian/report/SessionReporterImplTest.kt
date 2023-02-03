package com.chrrissoft.inglishwords.domian.report

import com.chrrissoft.inglishwords.data.session.NATIVE_REPORTS
import com.chrrissoft.inglishwords.data.session.SessionReportSaverImpl
import com.chrrissoft.inglishwords.data.session.TARGET_REPORTS
import com.chrrissoft.inglishwords.data.session.TOTAL_REPORTS
import com.chrrissoft.inglishwords.domian.report.expecteds.*
import com.chrrissoft.inglishwords.domian.report.expecteds.nativeLevelReportReportExpected
import com.chrrissoft.inglishwords.domian.report.expecteds.nativeWordsReportExpected
import com.chrrissoft.inglishwords.domian.report.expecteds.targetLevelReportReportExpected
import com.chrrissoft.inglishwords.domian.report.expecteds.targetWordsReportExpected
import com.chrrissoft.inglishwords.domian.report.expecteds.totalLevelReportReportExpected
import com.chrrissoft.inglishwords.domian.report.expecteds.totalWordsReportExpected
import com.chrrissoft.inglishwords.domian.report.results.wordsResultsSortByInterleaved
import com.chrrissoft.inglishwords.domian.report.results.wordsResultsSortByNativeFirst
import com.chrrissoft.inglishwords.domian.report.results.wordsResultsSortByRandom
import com.chrrissoft.inglishwords.domian.report.results.wordsResultsSortByTargetFirst
import org.junit.Test


internal class SessionReporterImplTest {

    private val cache = GamePlayDataCacheImpl()
    private val saver = SessionReportSaverImpl()
    private val reporter = SessionReporterImpl(cache, saver)

    private val expectedTargetWords = targetWordsReportExpected
    private val expectedNativeWords = nativeWordsReportExpected
    private val expectedTotalWords = totalWordsReportExpected

    private val expectedTargetLevels = targetLevelReportReportExpected
    private val expectedNativeLevels = nativeLevelReportReportExpected
    private val expectedTotalLevels = totalLevelReportReportExpected

    private val expectedTargetSession = targetSessionReportExpected
    private val expectedNativeSession = nativeSessionReportExpected
    private val expectedTotalSession = totalSessionReportExpected

    private val expectedTargetWordByLevel = targetWordsByLevelReportExpected
    private val expectedNativeWordByLevel = nativeWordsByLevelReportExpected
    private val expectedTotalWordByLevel = totalWordsByLevelReportExpected


    ////////////////////  saving by random  /////////////////////
    @Test
    fun buildTargetReportSortByRandom() {
        saveSortByRandom()
        reporter.save()
        assert(TARGET_REPORTS.value!!.reportStructure.words == expectedTargetWords)
        assert(TARGET_REPORTS.value!!.reportStructure.levels == expectedTargetLevels)
        assert(TARGET_REPORTS.value!!.reportStructure.session == expectedTargetSession)
        assert(TARGET_REPORTS.value!!.reportStructure.wordsByLevels == expectedTargetWordByLevel)
    }

    @Test
    fun buildNativeReportSortByRandom() {
        saveSortByRandom()
        reporter.save()
        assert(NATIVE_REPORTS.value!!.reportStructure.words == expectedNativeWords)
        assert(NATIVE_REPORTS.value!!.reportStructure.levels == expectedNativeLevels)
        assert(NATIVE_REPORTS.value!!.reportStructure.session == expectedNativeSession)
        assert(NATIVE_REPORTS.value!!.reportStructure.wordsByLevels == expectedNativeWordByLevel)
    }

    @Test
    fun buildTotalReportSortByRandom() {
        saveSortByRandom()
        reporter.save()
        assert(TOTAL_REPORTS.value!!.reportStructure.words == expectedTotalWords)
        assert(TOTAL_REPORTS.value!!.reportStructure.levels == expectedTotalLevels)
        assert(TOTAL_REPORTS.value!!.reportStructure.session == expectedTotalSession)
        assert(TOTAL_REPORTS.value!!.reportStructure.wordsByLevels == expectedTotalWordByLevel)
    }


    ////////////////////  saving by target first  /////////////////////
    @Test
    fun buildTargetReportSortByTargetFirst() {
        saveSortByTargetFirst()
        reporter.save()
        assert(TARGET_REPORTS.value!!.reportStructure.words == expectedTargetWords)
        assert(TARGET_REPORTS.value!!.reportStructure.levels == expectedTargetLevels)
        assert(TARGET_REPORTS.value!!.reportStructure.session == expectedTargetSession)
        assert(TARGET_REPORTS.value!!.reportStructure.wordsByLevels == expectedTargetWordByLevel)
    }

    @Test
    fun buildNativeReportSortByTargetFirst() {
        saveSortByTargetFirst()
        reporter.save()
        assert(NATIVE_REPORTS.value!!.reportStructure.words == expectedNativeWords)
        assert(NATIVE_REPORTS.value!!.reportStructure.levels == expectedNativeLevels)
        assert(NATIVE_REPORTS.value!!.reportStructure.session == expectedNativeSession)
        assert(NATIVE_REPORTS.value!!.reportStructure.wordsByLevels == expectedNativeWordByLevel)
    }

    @Test
    fun buildTotalReportSortByTargetFirst() {
        saveSortByTargetFirst()
        reporter.save()
        assert(TOTAL_REPORTS.value!!.reportStructure.words == expectedTotalWords)
        assert(TOTAL_REPORTS.value!!.reportStructure.levels == expectedTotalLevels)
        assert(TOTAL_REPORTS.value!!.reportStructure.session == expectedTotalSession)
        assert(TOTAL_REPORTS.value!!.reportStructure.wordsByLevels == expectedTotalWordByLevel)
    }


    ////////////////////  saving by native first  /////////////////////
    @Test
    fun buildTargetReportSortByNativeFirst() {
        saveSortByNativeFirst()
        reporter.save()
        assert(TARGET_REPORTS.value!!.reportStructure.words == expectedTargetWords)
        assert(TARGET_REPORTS.value!!.reportStructure.levels == expectedTargetLevels)
        assert(TARGET_REPORTS.value!!.reportStructure.session == expectedTargetSession)
        assert(TARGET_REPORTS.value!!.reportStructure.wordsByLevels == expectedTargetWordByLevel)
    }

    @Test
    fun buildNativeReportSortByNativeFirst() {
        saveSortByNativeFirst()
        reporter.save()
        assert(NATIVE_REPORTS.value!!.reportStructure.words == expectedNativeWords)
        assert(NATIVE_REPORTS.value!!.reportStructure.levels == expectedNativeLevels)
        assert(NATIVE_REPORTS.value!!.reportStructure.session == expectedNativeSession)
        assert(NATIVE_REPORTS.value!!.reportStructure.wordsByLevels == expectedNativeWordByLevel)
    }

    @Test
    fun buildTotalReportSortByNativeFirst() {
        saveSortByNativeFirst()
        reporter.save()
        assert(TOTAL_REPORTS.value!!.reportStructure.words == expectedTotalWords)
        assert(TOTAL_REPORTS.value!!.reportStructure.levels == expectedTotalLevels)
        assert(TOTAL_REPORTS.value!!.reportStructure.session == expectedTotalSession)
        assert(TOTAL_REPORTS.value!!.reportStructure.wordsByLevels == expectedTotalWordByLevel)
    }


    ////////////////////  saving by native first  /////////////////////
    @Test
    fun buildTargetReportSortByInterleaved() {
        saveSortByInterleaved()
        reporter.save()
        assert(TARGET_REPORTS.value!!.reportStructure.words == expectedTargetWords)
        assert(TARGET_REPORTS.value!!.reportStructure.levels == expectedTargetLevels)
        assert(TARGET_REPORTS.value!!.reportStructure.session == expectedTargetSession)
        assert(TARGET_REPORTS.value!!.reportStructure.wordsByLevels == expectedTargetWordByLevel)
    }

    @Test
    fun buildNativeReportSortByInterleaved() {
        saveSortByInterleaved()
        reporter.save()
        assert(NATIVE_REPORTS.value!!.reportStructure.words == expectedNativeWords)
        assert(NATIVE_REPORTS.value!!.reportStructure.levels == expectedNativeLevels)
        assert(NATIVE_REPORTS.value!!.reportStructure.session == expectedNativeSession)
        assert(NATIVE_REPORTS.value!!.reportStructure.wordsByLevels == expectedNativeWordByLevel)
    }

    @Test
    fun buildTotalReportSortByInterleaved() {
        saveSortByInterleaved()
        reporter.save()
        assert(TOTAL_REPORTS.value!!.reportStructure.words == expectedTotalWords)
        assert(TOTAL_REPORTS.value!!.reportStructure.levels == expectedTotalLevels)
        assert(TOTAL_REPORTS.value!!.reportStructure.session == expectedTotalSession)
        assert(TOTAL_REPORTS.value!!.reportStructure.wordsByLevels == expectedTotalWordByLevel)
    }


    ///////////////////////////  save  ///////////////////////////
    private fun saveSortByRandom() {
        wordsResultsSortByRandom.forEach { cache.save(it) }
    }

    private fun saveSortByTargetFirst() {
        wordsResultsSortByTargetFirst.forEach { cache.save(it) }
    }

    private fun saveSortByNativeFirst() {
        wordsResultsSortByNativeFirst.forEach { cache.save(it) }
    }

    private fun saveSortByInterleaved() {
        wordsResultsSortByInterleaved.forEach { cache.save(it) }
    }

}
