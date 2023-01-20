package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.editor.EditorImpl
import com.chrrissoft.inglishwords.domian.gameplay.keyboard.*
import com.chrrissoft.inglishwords.domian.gameplay.util.thereIsNext

class Levels {

    private val editor1 = EditorImpl(true)
    private val editor2 = EditorImpl()
    private val editor3 = EditorImpl()
    private val editor4 = EditorImpl()
    private val editor5 = EditorImpl()
    private val level1 = LevelImp(1, editor1, KeyboardLevel1Impl(editor1))
    private val level2 = LevelImp(2, editor2, KeyboardLevel2Impl(editor2))
    private val level3 = LevelImp(3, editor3, KeyboardLevel3Impl(editor3))
    private val level4 = LevelImp(4, editor4, KeyboardLevel4Impl(editor4))
    private val level5 = LevelImp(5, editor5, KeyboardLevel5Impl(editor5))


    private val levels = listOf(level1, level2, level3, level4, level5)
    val firstLevel = levels.first()

    var currentLevel = firstLevel
    fun thereIsNextLevel(block: (Level) -> Unit) : Boolean {
        return levels.thereIsNext(currentLevel) {
            currentLevel = it
            block(it)
        }
    }

    fun destroyLevels() { levels.forEach { it.destroy() } }

}