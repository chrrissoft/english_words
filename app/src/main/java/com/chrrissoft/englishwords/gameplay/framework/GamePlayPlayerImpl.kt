package com.chrrissoft.englishwords.gameplay.framework

import android.content.Context
import android.media.MediaPlayer
import com.chrrissoft.englishwords.R
import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GamePlayPlayerImpl @Inject constructor(
    private val player: MediaPlayer,
    @ApplicationContext private val context: Context,
) : GamePlayPlayer {

    override fun playFailedWord() {
        play(R.raw.word_fail)
    }

    override fun playWinWord() {
        play(R.raw.word_win)
    }

    override fun playFailedLetter() {
        play(R.raw.letter_fail)
    }

    override fun playWinLetter() {
        play(R.raw.letter_win)
    }

    override fun playSelectedWord() {
        play(R.raw.selected)
    }

    override fun playOnReplacement() {
        play(R.raw.on_replacement)
    }

    override fun playNextLevel() {
        play(R.raw.next_level)
    }

    override fun playEndGame() {
        play(R.raw.end_game)
    }

    override fun playMagicKeyAvailable() {
        play(R.raw.magic_available)
    }

    override fun playMagicKeyUnavailable() {
        play(R.raw.magic_unavailable)
    }

    private fun play(id: Int) {
        val des = context.resources.openRawResourceFd(id)
        player.reset()
        player.setDataSource(
            des.fileDescriptor,
            des.startOffset,
            des.length
        )
        des.close()
        player.setOnPreparedListener {
            player.start()
        }
        player.prepareAsync()
    }

    override fun release() {
        player.release()
    }

}
