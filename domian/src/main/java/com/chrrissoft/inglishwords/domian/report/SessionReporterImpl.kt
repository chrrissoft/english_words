package com.chrrissoft.inglishwords.domian.report

class SessionReporterImpl(

) : SessionReporter {

    private val storage = mutableMapOf<Int, WordReport>()

    override fun getCompleteReport(): Report {
        TODO("Not yet implemented")
    }

    override fun addWordReport(data: WordReport) {
        storage[data.level] = data
    }

}
