package com.luxoft.codingtest.utils

import java.util.Optional
import java.util.TreeSet
import java.text.DecimalFormat
import java.util.regex.Pattern


class ReportUtil {

    private val PATTERN_WORD_SIZE = Pattern.compile("\\(\\{.+}, (?<size>\\d*+)\\) -> .+")
    private val OUTPUT_FORMAT = "({%s}, %s) -> %s"
    private val AVERAGE_FORMATTER = DecimalFormat("0.#####")

    private val reportLines = TreeSet<String> { line1, line2 -> Integer.compare(getWordLength(line2), getWordLength(line1)) }

    fun add(numberOfChars: Int, vowels: String, average: Double) {
        reportLines.add(applyFormat(numberOfChars, vowels, average))
    }

    fun getReport(): String {
        val report = StringBuffer()

        var firstElement = false
        for (line in reportLines) {
            if (firstElement) {
                report.append(System.lineSeparator())
            }
            report.append(line)
            firstElement = true
        }
        return report.toString()
    }

    private fun applyFormat(numberOfChars: Int, vowels: String, average: Double): String {
        return String.format(OUTPUT_FORMAT,
                vowels.replace(".(?!$)".toRegex(), "$0, "),
                numberOfChars,
                AVERAGE_FORMATTER.format(average))
    }

    private fun getWordLength(lineReport: String): Int {
        return Integer.valueOf(Optional.of(PATTERN_WORD_SIZE.matcher(lineReport))
                .filter({ m -> m.find() }).map({ m -> m.group("size") }).orElse("-1"))
    }
}