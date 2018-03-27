package com.luxoft.codingtest

import com.luxoft.codingtest.engine.WordEngine
import com.luxoft.codingtest.utils.FileUtil
import com.luxoft.codingtest.utils.ReportUtil

private class Constants {
    companion object {
        const val INPUT_FILE = "INPUT.TXT"
        const val OUTPUT_FILE = "OUTPUT.TXT"
    }
}

fun main(args: Array<String>) {

    try {
        WordEngine (ReportUtil(), FileUtil(Constants.INPUT_FILE, Constants.OUTPUT_FILE)).process()
    } catch (e : Exception) {
        e.printStackTrace()
        System.exit(-1)
    }
}

