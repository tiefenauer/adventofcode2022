package info.tiefenauer.adventofcode2022

import java.io.File

const val alphabet = "abcdefghijklmnopqrstuvwxyz"
val priorities = "${alphabet}${alphabet.uppercase()}"
    .mapIndexed { index, c -> Pair(c, index + 1) }
    .associate { it }

fun main() {
    val result = File("src/main/resources/challenge03.txt")
        .readLines()
        .map { it.splitInHalf() }
        .map { findInvalidElement(it.first, it.second) }
        .map { priorities[it] }
        .sumOf { it!! }

    print(result) // 8233
}

fun findInvalidElement(first: String, second: String) =
    first.toCharArray().first { second.contains(it) }

fun String.splitInHalf() = Pair(substring(0, length / 2), substring(length / 2))
