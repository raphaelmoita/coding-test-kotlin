package com.luxoft.codingtest.utils

import org.slf4j.LoggerFactory
import java.io.IOException
import java.nio.file.Paths
import java.nio.file.Files
import java.io.FileNotFoundException
import java.nio.file.StandardOpenOption



class FileUtil(val inputFileName: String, val outputFileName: String) {

    private val logger = LoggerFactory.getLogger(FileUtil::class.java)

    init {
        validate(this.inputFileName)
        try {
            Files.deleteIfExists(Paths.get(this.outputFileName))
        } catch (e: IOException) {
            logger.warn("Error at time to delete old output file {} due to {}",
                    inputFileName, e.message, e)
        }
    }

    @Throws(IOException::class)
    fun read(): String {
        val path = Paths.get(this.inputFileName)
        return if (Files.isReadable(path) && Files.isRegularFile(path)) {
            String(Files.readAllBytes(path))
        } else {
            throw IOException("Error at time to read $inputFileName")
        }
    }

    @Throws(IOException::class)
    fun save(content: String) {
        val path = Paths.get(this.outputFileName)
        Files.write(path, content.toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
    }

    @Throws(FileNotFoundException::class)
    private fun validate(fileName: String) {
        if (Files.notExists(Paths.get(fileName))) {
            throw FileNotFoundException(fileName)
        }
    }
}