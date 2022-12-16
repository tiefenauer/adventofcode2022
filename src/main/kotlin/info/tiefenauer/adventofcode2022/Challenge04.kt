package info.tiefenauer.adventofcode2022

import java.io.File

fun main() {
    val ranges = File("src/main/resources/challenge04.txt").readLines()
        .map { it.split(",") }
        .map { it.toRanges() }

    val partOne = ranges
        .filter { it.overlapsFully() }
        .size

    val partTwo = ranges
        .filter { it.overlapsPartially() }
        .size

    println(partOne) // 475
    println(partTwo) // 825
}

private fun List<String>.toRanges() = map { it.split("-").toRange() }

private fun List<String>.toRange() = Pair(first().toInt(), last().toInt())

private fun List<Pair<Int, Int>>.overlapsPartially() =
    overlapsFully() || first().overlapsPartiallyWith(last()) || last().overlapsPartiallyWith(first())

private fun List<Pair<Int, Int>>.overlapsFully() =
    first().overlapsFullyWith(last()) || last().overlapsFullyWith(first())

private fun Pair<Int, Int>.overlapsFullyWith(other: Pair<Int, Int>) =
    first <= other.first && second >= other.second

private fun Pair<Int, Int>.overlapsPartiallyWith(other: Pair<Int, Int>) =
    first in other.first..other.second || second in other.first..other.second

