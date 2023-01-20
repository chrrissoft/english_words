package com.chrrissoft.inglishwords.domian.gameplay.util


fun <K, V> Map<K, V>.shuffled(): Map<K, V> {
    val keys = this.keys.shuffled()
    val values = this.values.shuffled()
    val map = mutableMapOf<K, V>()
    keys.forEachIndexed { index, k ->
        map[k] = values[index]
    }

    return map
}
