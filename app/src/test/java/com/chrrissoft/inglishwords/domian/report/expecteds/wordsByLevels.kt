package com.chrrissoft.inglishwords.domian.report.expecteds

import com.chrrissoft.inglishwords.domian.report.Duration.Companion.toDuration
import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport.*
import com.chrrissoft.inglishwords.domian.report.WordsByLevels.TargetWordsByLevelsImpl
import com.chrrissoft.inglishwords.domian.report.WordsByLevels.NativeWordsByLevelsImpl
import com.chrrissoft.inglishwords.domian.report.WordsByLevels.TotalWordsByLevelsImpl

private val targetLevelsWords = listOf(
    TargetWordReportImpl(
        failures = 1, mistakes = 4,
        translation = Translation("correr"),
        time = toDuration(10000), word = Word("run"),
    ),
    TargetWordReportImpl(
        failures = 2, mistakes = 5,
        translation = Translation("tecla"),
        time = toDuration(10000), word = Word("key"),
    ),
)

private val nativeLevelsWords = listOf(
    NativeWordReportImpl(
        failures = 1, mistakes = 4,
        translation = Translation("run"),
        time = toDuration(10000), word = Word("correr"),
    ),
    NativeWordReportImpl(
        failures = 2, mistakes = 5,
        translation = Translation("key"),
        time = toDuration(10000), word = Word("tecla"),
    ),
)

private val totalLevelsWords = listOf(
    TotalWordReportImpl(
        failures = 2, mistakes = 8,
        translation = Translation("correr"),
        time = toDuration(20000), word = Word("run"),
    ),
    TotalWordReportImpl(
        failures = 4, mistakes = 10,
        translation = Translation("tecla"),
        time = toDuration(20000), word = Word("key"),
    ),
)


internal val targetWordsByLevelReportExpected = listOf(
    TargetWordsByLevelsImpl(level = 1, targetLevelsWords),
    TargetWordsByLevelsImpl(level = 2, targetLevelsWords),
    TargetWordsByLevelsImpl(level = 3, targetLevelsWords),
    TargetWordsByLevelsImpl(level = 4, targetLevelsWords),
    TargetWordsByLevelsImpl(level = 5, targetLevelsWords),
)

internal val nativeWordsByLevelReportExpected = listOf(
    NativeWordsByLevelsImpl(level = 1, nativeLevelsWords),
    NativeWordsByLevelsImpl(level = 2, nativeLevelsWords),
    NativeWordsByLevelsImpl(level = 3, nativeLevelsWords),
    NativeWordsByLevelsImpl(level = 4, nativeLevelsWords),
    NativeWordsByLevelsImpl(level = 5, nativeLevelsWords),
)

internal val totalWordsByLevelReportExpected = listOf(
    TotalWordsByLevelsImpl(level = 1, totalLevelsWords),
    TotalWordsByLevelsImpl(level = 2, totalLevelsWords),
    TotalWordsByLevelsImpl(level = 3, totalLevelsWords),
    TotalWordsByLevelsImpl(level = 4, totalLevelsWords),
    TotalWordsByLevelsImpl(level = 5, totalLevelsWords),
)
