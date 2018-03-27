package com.luxoft.codingtest.utils

import java.util.TreeSet
import java.util.ArrayList
import java.util.regex.Pattern


class ContentUtil {

    companion object {

        private val PATTERN_ONLY_WORDS = Pattern.compile("(?<word>[a-zA-Z]{2,})")
        private val PATTERN_ONLY_VOWELS = Pattern.compile("(?<vowels>[aeiouAEIOU])")

        fun extractWords(content: String): Collection<String> {
            val matcher = PATTERN_ONLY_WORDS.matcher(content)
            val words = ArrayList<String>()
            while (matcher.find()) {
                words.add(matcher.group("word"))
            }
            return words
        }

        fun extractVowels(word: String): Collection<String> {
            val m = PATTERN_ONLY_VOWELS.matcher(word)
            val vowels = ArrayList<String>()
            while (m.find()) {
                vowels.add(m.group("vowels"))
            }
            return vowels
        }

        fun extractVowelsNoDupes(word: String): Collection<String> {
            return TreeSet(extractVowels(word))
        }
    }
}