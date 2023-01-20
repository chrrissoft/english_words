package com.chrrissoft.inglishwords.domian.gameplay

import kotlinx.coroutines.flow.Flow

interface GamePlay {

    val state: Flow<GameState>

}

