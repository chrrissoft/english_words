package com.chrrissoft.englishwords.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp


val Dp.px: Int
    @Composable get() = with(LocalDensity.current) {
        this@px.toPx().toInt()
    }

val Dp.middle: Dp
    get() = this.div(2)
