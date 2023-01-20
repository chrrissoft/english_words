package com.chrrissoft.inglishwords.domian.gameplay.util

fun <T, R> List<T>.thereIsNext(
    e: T,
    yes: (T) -> R,
    not: () -> R,
): R {
    val index = indexOf(e)
    if (index == -1) throw IllegalArgumentException("index out of range")
    return try {
        val next = get(index + 1)
        yes(next)
    } catch (_: IndexOutOfBoundsException) {
        not()
    }
}

fun <T, R> List<T>.thereIsNext(e: T, block: (T) -> R): Boolean {
    return thereIsNext(e, { block(it); true }, { false })
}

fun <T> List<T>.replace(o: T, n: T): List<T> {
    val index = indexOf(o)
    require(index != -1)
    val list = this.toMutableList()
    list.removeAt(index)
    list.add(index, n)
    return list
}

fun <T> List<T>.interleave(list: List<T>): List<T> {
    val newList = mutableListOf<T>()
    val toAdd: MutableList<T>
    val toGo = if (list.size <= size) {
        toAdd = this.toMutableList()
        list.toMutableList()
    } else {
        toAdd = list.toMutableList()
        this.toMutableList()
    }

    toGo.forEachIndexed { i, e ->
        newList.add(e)
        newList.add(toAdd[i])
    }
    repeat(toGo.size) {
        toAdd.removeFirst()
    }
    newList.addAll(toAdd)

    return newList
}

fun <T> margeInterleave(l1: List<T>, l2: List<T>): List<T> {
    return l1.interleave(l2)
}
