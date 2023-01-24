package com.chrrissoft.inglishwords.domian.gameplay

import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsManager
import com.chrrissoft.inglishwords.domian.gameplay.report.SessionReporter
import com.chrrissoft.inglishwords.domian.gameplay.report.WordReport
import com.chrrissoft.inglishwords.domian.gameplay.word.Word
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class GamePlayImpl(
    words: List<Word>,
    private val levelsManager: LevelsManager,
    private val sessionReporter: SessionReporter,
) : GamePlay {

    private val scope = MainScope()

    override val state = levelsManager.state

    init {
        levelsManager.setUp(words)
        onStateChange {
            if (it.end) {
                addReports(getReports())
                levelsManager.destroy()
            }
        }
    }

    private fun addReports(report: List<WordReport>) {
        report.forEach {
            sessionReporter.addWordReport(it)
        }
    }

    private fun getReports(): List<WordReport> {
        return levelsManager.getReports()
    }

    private fun onStateChange(
        block: suspend (GameState) -> Unit
    ) {
        scope.launch {
            levelsManager.state.collect {
                block(it)
            }
        }
    }

}
