package com.chrrissoft.inglishwords.domian.gameplay.editor


/**
 * represent the result data of an editor session
 * */
interface EditorReport {
    val time: Long
    val text: String
    val failed: Boolean
    val mistakes: Int
}
