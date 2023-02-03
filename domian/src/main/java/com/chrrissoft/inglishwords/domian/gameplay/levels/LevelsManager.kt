package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.GameState
import com.chrrissoft.inglishwords.domian.report.WordResult
import com.chrrissoft.inglishwords.domian.gameplay.word.Word
import kotlinx.coroutines.flow.StateFlow

interface LevelsManager {

    val state: StateFlow<GameState>

    fun getReports() : List<WordResult>

    fun setUp(words: List<Word>)

    fun destroy()

}
