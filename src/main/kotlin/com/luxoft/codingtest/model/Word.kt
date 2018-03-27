package com.luxoft.codingtest.model

import com.luxoft.codingtest.utils.ContentUtil
import java.util.Optional
import java.util.stream.Collectors


data class Word(val word: String) {

    val numOfCharacters: Int
    val numOfVowels: Int
    val vowelsToString: String

    init {
        this.validate(word)
        this.vowelsToString = ContentUtil.extractVowelsNoDupes(word).stream().collect(Collectors.joining())
        this.numOfCharacters = word.length
        this.numOfVowels = ContentUtil.extractVowels(word).size
    }

    private fun validate(word: String) {
        Optional.of(word).filter { w -> !w.isEmpty() && !w.contains(" ") && word.length > 1 }
                .orElseThrow { IllegalStateException("Invalid word: [$word]") }
    }
}
