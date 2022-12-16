package info.tiefenauer.adventofcode2022

import java.io.File

typealias Round = Pair<OpponentChoice, MyChoice>

enum class OpponentChoice(val choice: String) {
    ROCK("A"),
    PAPER("B"),
    SCISSORS("C");

    companion object {
        fun parse(s: String) = values().find { it.choice == s }
    }
}

enum class MyChoice(val choice: String) {
    ROCK("X"),
    PAPER("Y"),
    SCISSORS("Z");

    companion object {
        fun parse(s: String) = MyChoice.values().find { it.choice == s }
    }
}

fun main() {
    val result = File("src/main/resources/challenge02.txt")
        .readLines()
        .map { it.split(" ") }
        .mapNotNull { it.toRound() }
        .sumOf { calculateTotalScore(it) }

    print(result)
}

private fun List<String>.toRound() = when (true) {
    (size == 2) -> Pair(OpponentChoice.parse(first())!!, MyChoice.parse(last())!!)
    else -> null
}

fun calculateTotalScore(round: Round) =
    calculateShapeScore(round.second) + calculateWinOrLoss(round)

fun calculateShapeScore(myChoice: MyChoice) = when (myChoice) {
    MyChoice.ROCK -> 1
    MyChoice.PAPER -> 2
    MyChoice.SCISSORS -> 3
}

fun calculateWinOrLoss(round: Round) = when (true) {
    (round.isDraw()) -> 3
    (round.isWin()) -> 6 // win
    else -> 0 // loss
}

private fun Round.isDraw() = when (true) {
    (first == OpponentChoice.PAPER && second == MyChoice.PAPER),
    (first == OpponentChoice.SCISSORS && second == MyChoice.SCISSORS),
    (first == OpponentChoice.ROCK && second == MyChoice.ROCK) -> true

    else -> false
}


fun Round.isWin() = when (true) {
    (first == OpponentChoice.PAPER && second == MyChoice.SCISSORS),
    (first == OpponentChoice.SCISSORS && second == MyChoice.ROCK),
    (first == OpponentChoice.ROCK && second == MyChoice.PAPER) -> true

    else -> false
}
