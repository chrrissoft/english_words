package com.chrrissoft.inglishwords.domian.gameplay

import com.chrrissoft.inglishwords.domian.gameplay.levels.*
import com.chrrissoft.inglishwords.domian.gameplay.report.SessionReporter
import com.chrrissoft.inglishwords.domian.gameplay.report.WordReport
import com.chrrissoft.inglishwords.domian.gameplay.word.Word
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GamePlayImpl(
    words: List<Word>,
    private val levelsManager: LevelsManager,
    private val sessionReporter: SessionReporter,
) : GamePlay {

    private val scope = MainScope()

    private val _state = MutableStateFlow(GameState())
    override val state = _state.asStateFlow()

    init {
        levelsManager.setUp(words)
        onStateChange {
            withContext(Main) { updateState(it) }
            if (it.end) {
                addReports(getReports())
                levelsManager.destroy()
            }
        }
    }

    private fun updateState(state: LevelsManagerState) {
        _state.update {
            it.copy(
                level = state.level,
                endGame = state.end,
                failed = state.failed,
                editor = state.editor,
                keyboard = state.keyboard,
                replacement = state.replacement,
            )
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
        block: suspend (LevelsManagerState) -> Unit
    ) {
        scope.launch {
            levelsManager.state.collect {
                block(it)
            }
        }
    }

}
