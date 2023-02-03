package com.chrrissoft.inglishwords.domian.report


interface SessionReporter {

    fun save()

    fun addWordReport(data: WordResult)

}
