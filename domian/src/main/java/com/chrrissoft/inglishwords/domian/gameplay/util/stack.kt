package com.chrrissoft.inglishwords.domian.gameplay.util

import java.util.Stack

fun <T> stack(count: Int, content: () -> T) : Stack<T> {
    val stack = Stack<T>()
    repeat(count) {
        stack.add(content())
    }
    return stack
}


fun <T> Stack<T>.myPush(e: T, n: Int = 1): Stack<T> {
    repeat(n) { push(e) }
    return this
}