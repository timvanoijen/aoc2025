package day5

import utils.readLines
import kotlin.math.max
import kotlin.math.min

private const val DAY = 5

fun main() {
    val lines = readLines(DAY, test = false)
    val ranges = lines
        .takeWhile { it.isNotBlank() }
        .map { it.split('-').map(String::toLong) }

    val total = ranges.fold(emptyList<List<Long>>()) { rangeList, r2Init ->
        val r2NonOverlaps = rangeList.fold(listOf(r2Init)) { r2Parts, r1 ->
            r2Parts.flatMap { r2 ->
                buildList {
                    if (r2[0] < r1[0]) add(listOf(r2[0], min(r2[1], r1[0] - 1)))
                    if (r2[1] > r1[1]) add(listOf(max(r2[0], r1[1] + 1), r2[1]))
                }
            }
        }
        rangeList + r2NonOverlaps
    }.sumOf { it[1] - it[0] + 1 }

    println(total)
}