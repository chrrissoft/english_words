package com.chrrissoft.inglishwords.domian.gameplay

import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsManager
import com.chrrissoft.inglishwords.domian.gameplay.levels.TargetWordResultImpl
import com.chrrissoft.inglishwords.domian.report.SessionReporter
import com.chrrissoft.inglishwords.domian.report.WordResult
import com.chrrissoft.inglishwords.domian.gameplay.word.Word
import com.chrrissoft.inglishwords.domian.report.WordResult.NativeText
import com.chrrissoft.inglishwords.domian.report.WordResult.TargetText
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
                sessionReporter.save()
                levelsManager.destroy()
            }
        }
    }

    private fun addReports(report: List<WordResult>) {
//        report.forEach {
//            sessionReporter.addWordReport(it)
//        }
    }

    private fun getReports(): List<WordResult> {
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
