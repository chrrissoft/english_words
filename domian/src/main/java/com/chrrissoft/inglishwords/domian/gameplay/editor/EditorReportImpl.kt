package com.chrrissoft.inglishwords.domian.gameplay.editor

internal data class EditorReportImpl(
    override val time: Long,
    override val text: String,
    override val failed: Boolean,
    override val mistakes: Int
) : EditorReport
