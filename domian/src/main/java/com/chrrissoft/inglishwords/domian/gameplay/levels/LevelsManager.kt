package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.report.WordReport
import com.chrrissoft.inglishwords.domian.gameplay.word.Word
import kotlinx.coroutines.flow.StateFlow

interface LevelsManager {

    val state: StateFlow<LevelsManagerState>

    fun getReports() : List<WordReport>

    fun setUp(words: List<Word>)

    fun destroy()

}
