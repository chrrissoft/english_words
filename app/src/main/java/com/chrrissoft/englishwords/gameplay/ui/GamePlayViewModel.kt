package com.chrrissoft.englishwords.gameplay.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrrissoft.inglishwords.data.session.SessionReportSaverImpl
import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import com.chrrissoft.inglishwords.domian.gameplay.GamePlayImpl
import com.chrrissoft.inglishwords.domian.gameplay.levels.Levels
import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsManagerImpl
import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsSettingsImpl
import com.chrrissoft.inglishwords.domian.report.SessionReporterImpl
import com.chrrissoft.inglishwords.domian.gameplay.word.TranslatedWord.SpanishTranslatedWord
import com.chrrissoft.inglishwords.domian.gameplay.word.Word.EnglishWord
import com.chrrissoft.inglishwords.domian.report.GamePlayDataCacheImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamePlayViewModel @Inject constructor(
    player: GamePlayPlayer
) : ViewModel() {

    private val settings = LevelsSettingsImpl()
    private val levels = Levels(player)
    private val manager = LevelsManagerImpl(levels, settings, player)
    private val list = buildList {
        add(EnglishWord("see", SpanishTranslatedWord("ver")))
        add(EnglishWord("get", SpanishTranslatedWord("dar")))
//        add(EnglishWord("two", SpanishTranslatedWordImpl("dos")))
    }
    private val session = SessionReporterImpl(GamePlayDataCacheImpl(), SessionReportSaverImpl())
    private val game = GamePlayImpl(list, manager, session)

    private val _state = MutableStateFlow(GamePlayScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            game.state.collect { state ->
                _state.update {
                    it.copy(
                        editor = state.editor,
                        keyboard = state.keyboard,
                        level = state.level,
                        words = state.words,
                        translated = state.translatedWord,
                        replacement = state.replacement,
                        failures = state.failed,
                    )
                }
            }
        }
    }

}
