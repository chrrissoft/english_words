package com.chrrissoft.inglishwords.domian.gameplay


data class Steps(val total: Int = 0, val current: Int = 1) {
    fun advance(): Steps {
        return Steps(total, current + 1)
    }
}