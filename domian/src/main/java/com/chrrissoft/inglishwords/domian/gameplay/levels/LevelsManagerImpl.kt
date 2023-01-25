package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import com.chrrissoft.inglishwords.domian.gameplay.GameState
import com.chrrissoft.inglishwords.domian.gameplay.Steps
import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorReport
import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorState
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardLanguage
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.KeyboardState
import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsSettings.Order.*
import com.chrrissoft.inglishwords.domian.gameplay.levels.Word.TargetWord
import com.chrrissoft.inglishwords.domian.gameplay.levels.Word.TranslatedWord
import com.chrrissoft.inglishwords.domian.report.WordReport
import com.chrrissoft.inglishwords.domian.gameplay.util.margeInterleave
import com.chrrissoft.inglishwords.domian.gameplay.util.thereIsNext
import com.chrrissoft.inglishwords.domian.gameplay.word.TranslatedWord.SpanishTranslatedWordImpl
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.chrrissoft.inglishwords.domian.gameplay.word.Word as InputWord

class LevelsManagerImpl(
    private val levels: Levels,
    private val settings: LevelsSettings,
    private val player: GamePlayPlayer
) : LevelsManager {

    private val scope = MainScope()

    private lateinit var currentWord: Word
    private lateinit var inputWords: List<Word>
    private val words = mutableListOf<Word>()
    private val failedWords = mutableMapOf<Word, WordReport>()

    private val reports = mutableListOf<WordReport>()

    private val _state = MutableStateFlow(GameState(Steps(levels.totalLevels)))
    override val state = _state.asStateFlow()

    private data class SplitWord(
        val target: List<TargetWord>,
        val translation: List<TranslatedWord>
    )

    init {
        observeCurrentLevel(levels.firstLevel)
    }

    private fun observeCurrentLevel(level: Level) {
        collectCurrentLevel(
            level = level,
            onEditorChange = {
                withContext(Main) { updateEditor(it) }

                onEditorFailed(it) {
                    addFailureWord(currentWord, level)
                    incrementFailedWordsState()
                    playFailedWord()
                    advance()
                }

                onEditorComplete(it) {
                    var report = generateWordReport(level)
                    onReplacement {
                        report = sumDeleteReport(currentWord, report)
                        decrementFailedWordsState()
                    }
                    addReport(report)
                    playWinWord()
                    advance()
                }
            },
            onKeyboardChange = { updateKeyboard(it) }
        )
    }

    private fun collectCurrentLevel(
        level: Level,
        onEditorChange: suspend (EditorState) -> Unit,
        onKeyboardChange: suspend (KeyboardState) -> Unit,
    ) {
        scope.launch { level.editorState.collect { onEditorChange(it) } }
        scope.launch { level.keyboardState.collect { onKeyboardChange(it) } }
    }

    private fun onEditorComplete(
        editor: EditorState, block: () -> Unit
    ) {
        if (editor.complete) block()
    }

    private fun onEditorFailed(
        editor: EditorState, block: () -> Unit
    ) {
        if (editor.failed) block()
    }

    private fun addReport(report: WordReport) {
        reports.add(report)
    }

    private fun updateKeyboard(keyboard: KeyboardState) {
        _state.update { it.copy(keyboard = keyboard) }
    }

    private fun updateEditor(editor: EditorState) {
        _state.update {
            it.copy(editor = editor)
        }
    }

    override fun getReports(): List<WordReport> {
        return reports
    }

    private fun generateWordReport(level: Level): WordReport {
        val editorReport = getEditorReport(level)
        return WordReportImpl(
            level = level.number,
            text = currentWord.text,
            time = editorReport.time,
            index = currentWord.index,
            mistakes = editorReport.mistakes,
            failures = if (editorReport.failed) 1 else 0,
            language = when (currentWord) {
                is TargetWord -> WordReport.Language.Target
                is TranslatedWord -> WordReport.Language.Translation
            },
        )
    }

    private fun getEditorReport(level: Level): EditorReport {
        return level.getReport()
    }

    private fun advance() {
        val wordUpdated = thereNextWord(currentWord) {
            updateWordNumberState(words.indexOf(it).plus(1))
            nextWord(levels.currentLevel, it)
            updateState()
        }

        if (wordUpdated) return

        val setMissWords = thereFailedWord { words ->
            val split = splitFailedWords(words)
            val margeWords = margeWords(split.target, split.translation)
            setFailedWords(levels.currentLevel, margeWords)
            updateReplacementState(value = true)
            updateState()
        }

        if (setMissWords) return

        val setLevel = thereNextLevel { level ->
            updateCurrentLevel(level)
            resetWords(level, inputWords)
            updateReplacementState(value = false)
            observeCurrentLevel(level)
            playNextLevel()
            updateState()
        }

        if (setLevel) return

        setEnd()
        playEndGame()
    }

    private fun updateState() {
        _state.update {
            it.copy(
                translatedWord = currentWord.translated,
                words = it.words.advance()
            )
        }
    }

    private fun playWinWord() {
        player.playWinWord()
    }

    private fun playNextLevel() {
        player.playNextLevel()
    }

    private fun playEndGame() {
        player.playEndGame()
    }

    private fun updateReplacementState(value: Boolean) {
        _state.update { it.copy(replacement = value) }
    }

    private fun thereNextWord(
        word: Word, block: (Word) -> Unit
    ): Boolean {
        return words.thereIsNext(word) {
            block(it)
        }
    }

    private fun thereNextLevel(block: (Level) -> Unit): Boolean {
        return levels.thereIsNextLevel { block(it) }
    }

    private fun nextWord(level: Level, word: Word) {
        updateCurrentWord(word)
        resetEditor(level, word.text)
        resetKeyboard(
            level, word.text, when (word.lang) {
                Word.Language.English -> KeyboardLanguage.English
                Word.Language.Spanish -> KeyboardLanguage.Spanish
            }
        )
    }

    private fun updateCurrentWord(word: Word) {
        currentWord = word
    }

    private fun updateWordNumberState(n: Int) {
        _state.update {
            it.copy(words = it.words.copy(current = n))
        }
    }

    private fun updateCurrentLevel(level: Level) {
        _state.update {
            it.copy(level = it.level.copy(current = level.number))
        }
    }

    private fun resetKeyboard(level: Level, word: String, lang: KeyboardLanguage) {
        level.resetKeyboard(word, lang)
    }

    private fun resetEditor(level: Level, word: String) {
        level.resetEditor(word)
    }

    private fun setEnd() {
        _state.update { it.copy(end = true) }
    }


////////////////////////////////// failed //////////////////////////////////

    private fun thereFailedWord(block: (List<Word>) -> Unit): Boolean {
        return if (failedWords.isNotEmpty()) {
            block(failedWords.map { it.key })
            true
        } else false
    }

    private fun addFailureWord(word: Word, level: Level) {
        var report = generateWordReport(level)
        wordIsFailed(word) {
            report = sumDeleteReport(it, report)
        }
        failedWords[word] = report
    }

    private fun playFailedWord() {
        player.playFailedWord()
    }

    private fun onReplacement(block: () -> Unit) {
        if (_state.value.replacement) block()
    }

    private fun incrementFailedWordsState() {
        _state.update {
            val increment = it.failed + 1
            it.copy(failed = increment)
        }
    }

    private fun decrementFailedWordsState() {
        _state.update {
            val increment = it.failed - 1
            it.copy(failed = increment)
        }
    }

    private fun setFailedWords(level: Level, words: List<Word>) {
        this.words.clear()
        this.words.addAll(words)
        nextWord(level, words.first())
    }

    private fun resetWords(level: Level, words: List<Word>) {
        this.words.clear()
        this.words.addAll(words)
        nextWord(level, words.first())
    }

    private fun wordIsFailed(word: Word, block: (Word) -> Unit): Boolean {
        return if (failedWords.containsKey(word)) {
            block(word)
            true
        } else false
    }

    private fun sumDeleteReport(word: Word, report: WordReport): WordReport {
        val value = failedWords.remove(word)
        requireNotNull(value)
        return value.sum(report)
    }

    private fun splitFailedWords(words: List<Word>): SplitWord {
        val translation = words.filterIsInstance<TranslatedWord>()
        val target = words.filterIsInstance<TargetWord>()
        return SplitWord(target, translation)
    }

    ////////////////////////////////// set up //////////////////////////////////

    override fun setUp(words: List<InputWord>) {
        initializeList(words)
        updateCurrentWord(this.words.first())
        resetKeyboard(
            levels.firstLevel, currentWord.text, when (currentWord.lang) {
                Word.Language.English -> KeyboardLanguage.English
                Word.Language.Spanish -> KeyboardLanguage.Spanish
            }
        )
        resetEditor(levels.firstLevel, currentWord.text)
        setUpState(words.size, currentWord.translated)
    }

    private fun setUpState(
        wordsNumber: Int,
        translatedWord: String,
    ) {
        _state.update {
            it.copy(
                translatedWord = translatedWord,
                words = Steps(total = wordsNumber),
            )
        }
    }

    private fun initializeList(words: List<InputWord>) {
        val splits = splitWords(words)
        inputWords = margeWords(splits.target, splits.translation)
        this.words.addAll(inputWords)
    }

    private fun splitWords(words: List<InputWord>): SplitWord {
        val translation = words
            .mapIndexed { i, word ->
                TranslatedWord(
                    index = i,
                    translated = word.text,
                    text = word.translation.text,
                    lang = when (word.translation) {
                        is SpanishTranslatedWordImpl -> Word.Language.Spanish
                    }
                )
            }
        val target = words.mapIndexed { i, word ->
            TargetWord(
                index = i,
                text = word.text,
                translated = word.translation.text,
                lang = when (word) {
                    is InputWord.EnglishWord -> Word.Language.English
                }
            )
        }
        return SplitWord(target, translation)
    }

    private fun margeWords(
        target: List<TargetWord>, translation: List<TranslatedWord>
    ): List<Word> {
        return when (settings.order) {
            Interleaved -> {
                margeInterleave(target, translation)
            }
            Random -> {
                margeInterleave(target, translation).shuffled()
            }
            TranslationFirst -> {
                translation + target
            }
            TranslationLast -> {
                target + translation
            }
        }
    }

    override fun destroy() {
        scope.cancel()
        levels.destroyLevels()
    }

}
