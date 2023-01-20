package com.chrrissoft.inglishwords.domian.gameplay.levels

import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsSettings.Order
import com.chrrissoft.inglishwords.domian.gameplay.levels.LevelsSettings.Order.Interleaved

data class LevelsSettingsImpl(
    override val order: Order = Interleaved
) : LevelsSettings
