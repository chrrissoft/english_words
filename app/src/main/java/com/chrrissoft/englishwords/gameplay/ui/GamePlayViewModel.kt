package com.chrrissoft.englishwords.gameplay.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrrissoft.inglishwords.domian.gameplay.GamePlayImpl
import com.chrrissoft.inglishwords.domian.gameplay.levels.Levels
import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsManagerImpl
import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsSettingsImpl
import com.chrrissoft.inglishwords.domian.gameplay.report.SessionReporterImpl
import com.chrrissoft.inglishwords.domian.gameplay.word.TranslatedWord.SpanishTranslatedWordImpl
import com.chrrissoft.inglishwords.domian.gameplay.word.Word.EnglishWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamePlayViewModel @Inject constructor() : ViewModel() {

    private val settings = LevelsSettingsImpl()
    private val levels = Levels()
    private val manager = LevelsManagerImpl(levels, settings)
    private val list = buildList {
        add(EnglishWord("see", SpanishTranslatedWordImpl("ver")))
        add(EnglishWord("love", SpanishTranslatedWordImpl("amor")))
        add(EnglishWord("two", SpanishTranslatedWordImpl("dos")))
    }
    private val session = SessionReporterImpl()
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
                        replacement = state.replacement,
                        failed = state.failed,
                    )
                }
            }
        }
    }

}
