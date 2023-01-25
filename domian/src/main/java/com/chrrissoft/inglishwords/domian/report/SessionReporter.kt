package com.chrrissoft.inglishwords.domian.report


interface SessionReporter {

    fun getCompleteReport(): Report

    fun addWordReport(data: WordReport)

}
