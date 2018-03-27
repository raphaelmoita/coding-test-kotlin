package com.luxoft.codingtest.engine

import com.luxoft.codingtest.model.Word
import com.luxoft.codingtest.utils.ContentUtil
import com.luxoft.codingtest.utils.FileUtil
import com.luxoft.codingtest.utils.ReportUtil
import org.slf4j.LoggerFactory
import java.io.IOException


class WordEngine(private val reportUtil: ReportUtil, private val fileUtil: FileUtil) {

    private val logger = LoggerFactory.getLogger(WordEngine::class.java)

    @Throws(IOException::class)
    fun process() {
        logger.info("Starting to process the words from file {}", fileUtil.inputFileName)

        val mapValues = ContentUtil.extractWords(fileUtil.read())
                .map { w -> Word(w) }
                .groupBy { it.numOfCharacters }
                .mapValues {
                    it.value.groupBy { it.vowelsToString }
                            .mapValues {
                                it.value.groupBy { it.numOfVowels }
                                        .keys.average()}
                }

        mapValues.forEach( { word ->
            word.value.forEach( { vowel, average -> reportUtil.add(word.key, vowel, average)} )
        } )

        val outPutContent = reportUtil.getReport()
        logger.debug("Generated report:{}{}", System.lineSeparator(), outPutContent)

        logger.info("Recording data to {} ...", fileUtil.outputFileName)
        fileUtil.save(outPutContent)
    }
}