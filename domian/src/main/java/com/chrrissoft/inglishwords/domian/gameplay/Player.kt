package com.chrrissoft.inglishwords.domian.gameplay

interface GamePlayPlayer {

    fun playFailedWord()
    fun playWinWord()

    fun playFailedLetter()
    fun playWinLetter()

    fun playSelectedWord()

    fun playOnReplacement()

    fun playNextLevel()
    fun playEndGame()

    fun playMagicKeyAvailable()
    fun playMagicKeyUnavailable()

    fun release()
}
