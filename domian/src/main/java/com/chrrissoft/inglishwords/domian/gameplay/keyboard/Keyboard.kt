package com.chrrissoft.inglishwords.domian.gameplay.keyboard

import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import com.chrrissoft.inglishwords.domian.gameplay.editor.Editor
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.Key.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class Keyboard(
    private val editor: Editor,
    private val player: GamePlayPlayer
) {

    abstract class KeyboardLevel1(editor: Editor, player: GamePlayPlayer) : Keyboard(editor, player)
    abstract class KeyboardLevel2(editor: Editor, player: GamePlayPlayer) : Keyboard(editor, player)
    abstract class KeyboardLevel3(editor: Editor, player: GamePlayPlayer) : Keyboard(editor, player)
    abstract class KeyboardLevel4(editor: Editor, player: GamePlayPlayer) : Keyboard(editor, player)
    abstract class KeyboardLevel5(editor: Editor, player: GamePlayPlayer) : Keyboard(editor, player)

    protected val mutableState = MutableStateFlow(KeyboardState())
    val state = mutableState.asStateFlow()

    private inner class DeleteKeyImpl(
        override val coordinates: Coordinates
    ) : DeleteKey {
        override fun onClick() {
            deleteText()
        }
    }

    private inner class SpacerKeyImpl(
        override val coordinates: Coordinates
    ) : SpacerKey {
        override fun onClick() {}
    }

    private inner class MagicKeyImpl(
        override var breaks: Int,
        override val coordinates: Coordinates,
    ) : MagicKey {
        override fun onClick() {
            thereAreLettersBreaks(
                yes = { letter ->
                    player.playMagicKeyAvailable()
                    findKey(letter).onClick()
                    mutableState.update {
                        val decrement = it.breaks - 1
                        breaks = decrement
                        it.copy(breaks = decrement)
                    }
                },
                not = { player.playMagicKeyUnavailable() }
            )
        }
    }

    protected fun setText(text: String): Boolean {
        return if(editor.setText(text)) {
            player.playWinWord()
            true
        } else {
            player.playFailedWord()
            false
        }
    }

    fun reset(word: String, language: KeyboardLanguage) {
        mutableState.update {
            it.copy(
                breaks = KeyboardState.BREAKS_LIMIT,
                structure = buildStructure(word, language),
            )
        }
    }

    protected abstract fun deleteText()
    protected abstract fun findKey(char: String): Key<*>

    abstract fun buildEnglishStructure(word: String): List<Key<*>>
    abstract fun buildSpanishStructure(word: String): List<Key<*>>

    private fun buildStructure(word: String, lang: KeyboardLanguage): List<Key<*>> {
        return when (lang) {
            KeyboardLanguage.English -> buildEnglishStructure(word)
            KeyboardLanguage.Spanish -> buildSpanishStructure(word)
        }
    }

    private fun thereAreLettersBreaks(
        yes: (String) -> Unit,
        not: () -> Unit,
    ) {
        val letter = editor.getNextCorrectLetter()
        if (mutableState.value.breaks != 0) {
            yes(letter)
        } else not()
    }

    protected fun buildOthersKeys(): List<Key<*>> {
        return buildList {
            add(DeleteKeyImpl(Coordinates.deleteCoordinates))
            add(MagicKeyImpl(KeyboardState.BREAKS_LIMIT, Coordinates.magicCoordinates))
            add(SpacerKeyImpl(Coordinates.spacerCoordinates))
        }
    }

}
