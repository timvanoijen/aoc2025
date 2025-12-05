package day5

import utils.readLines

private const val DAY = 5

fun main() {
    val lines = readLines(DAY, test = false)
    val ranges = lines
        .takeWhile { it.isNotBlank() }
        .map { it.split('-').map(String::toLong) }

    val ids = lines.subList(ranges.size + 1, lines.size)
        .map(String::toLong)

    val total = ids.count { id ->
        ranges.any { range ->
            id in (range[0]..range[1])
        }
    }

    println(total)
}