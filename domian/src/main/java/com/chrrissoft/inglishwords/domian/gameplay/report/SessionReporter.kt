package com.chrrissoft.inglishwords.domian.gameplay.report


interface SessionReporter {

    fun getSimpleReport(): SimpleSessionReport

    fun getCompleteReport(): CompleteReport

    fun addWordReport(data: WordReport)

}
