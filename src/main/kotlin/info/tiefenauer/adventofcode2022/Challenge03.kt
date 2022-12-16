package info.tiefenauer.adventofcode2022

import java.io.File

const val alphabet = "abcdefghijklmnopqrstuvwxyz"
val priorities = "${alphabet}${alphabet.uppercase()}"
    .mapIndexed { index, c -> Pair(c, index + 1) }
    .associate { it }

fun main() {
    val lines = File("src/main/resources/challenge03.txt").readLines()

    val partOne = lines
        .map { it.splitInHalf() }
        .map { findInvalidElement(it.first, it.second) }
        .map { priorities[it] }
        .sumOf { it!! }

    val partTwo = lines.chunked(3)
        .map { it.findBadge() }
        .map { priorities[it] }
        .sumOf { it!! }

    println(partOne) // 8233
    println(partTwo) // 2821
}

private fun List<String>.findBadge() = first().toCharArray().find { all { s -> s.contains(it) } }

fun findInvalidElement(first: String, second: String) =
    first.toCharArray().first { second.contains(it) }

fun String.splitInHalf() = Pair(substring(0, length / 2), substring(length / 2))
