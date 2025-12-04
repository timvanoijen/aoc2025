package day3

import utils.readLines

private const val DAY = 3

fun main() {
    val lines = readLines(DAY, test = false)
    val n = 12

    val total = lines.sumOf { line ->
        val digits = line.map { c -> c.digitToInt() }
        val cnt = digits.count()

        val (_, total) = (0..<n).fold(-1 to 0L) { (maxIdx, total), d ->
            val maxIdxUpdated = (maxIdx + 1..<(cnt - (n - 1) + d)).maxBy { digits[it] }
            val totalUpdated = total * 10 + digits[maxIdxUpdated]
            maxIdxUpdated to totalUpdated
        }
        total
    }

    println(total)
}