package info.tiefenauer.adventofcode2022

import java.io.File

fun main() {
    val result = File("src/main/resources/challenge01.txt")
        .readLines()
        .groupNonEmptyItems()
        .map { calories -> calories.fold(0) { acc, cal -> acc + cal.toInt() } }
        .maxOf { it }
    print(result) // 68787
}


fun List<String>.groupNonEmptyItems() = fold(ArrayList<MutableList<String>>()) { list, item ->
    list.apply {
        if (item.isBlank() || list.isEmpty()) {
            add(mutableListOf())
        } else {
            last().add(item)
        }
    }
}.map { it.toList() }
