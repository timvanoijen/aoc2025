package day6

import utils.readLines

private const val DAY = 6

fun main() {
    val lines = readLines(DAY, test = false)
    val sums = lines.dropLast(1).map { it.split(" ").filter(String::isNotBlank) }
    val ops = lines.last().split(" ").filter(String::isNotBlank)
    val (h, w) = sums.count() to sums[0].count()

    val total = (0..<w).sumOf { x ->
        val op: (Long, Long) -> Long = if (ops[x] == "*") Long::times else Long::plus
        (0..<h).map { y -> sums[y][x].toLong() }.reduce(op)
    }

    println(total)
}