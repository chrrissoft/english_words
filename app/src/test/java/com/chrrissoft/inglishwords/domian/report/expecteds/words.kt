package com.chrrissoft.inglishwords.domian.report.expecteds

import com.chrrissoft.inglishwords.domian.report.Duration.Companion.toDuration
import com.chrrissoft.inglishwords.domian.report.ReportPart.WordReport.*


internal val targetWordsReportExpected = listOf(
    TargetWordReportImpl(
        time = toDuration((10000L).times(5)),
        failures = 5,
        mistakes = 20,
        word = Word("run"),
        translation = Translation("correr")
    ),
    TargetWordReportImpl(
        time = toDuration((10000L).times(5)),
        failures = 10,
        mistakes = 25,
        word = Word("key"),
        translation = Translation("tecla")
    ),
)

internal val nativeWordsReportExpected = listOf(
    NativeWordReportImpl(
        time = toDuration((10000L).times(5)),
        failures = 5,
        mistakes = 20,
        word = Word("correr"),
        translation = Translation("run")
    ),
    NativeWordReportImpl(
        time = toDuration((10000L).times(5)),
        failures = 10,
        mistakes = 25,
        word = Word("tecla"),
        translation = Translation("key")
    )
)

internal val totalWordsReportExpected = listOf(
    TotalWordReportImpl(
        time = toDuration((20000L).times(5)),
        failures = 10,
        mistakes = 40,
        word = Word("run"),
        translation = Translation("correr")
    ),
    TotalWordReportImpl(
        time = toDuration((20000L).times(5)),
        failures = 20,
        mistakes = 50,
        word = Word("key"),
        translation = Translation("tecla")
    )
)
