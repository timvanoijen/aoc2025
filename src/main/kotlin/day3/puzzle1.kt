package day3

import utils.readLines

private const val DAY = 3

fun main() {
    val lines = readLines(DAY, test = false)

    val total = lines.sumOf { line ->
        val digits = line.map { c -> c.digitToInt() }
        val cnt = digits.count()

        val leftIdx = (0..<(cnt - 1)).maxBy { digits[it] }
        val rightIdx = (leftIdx + 1..<cnt).maxBy { digits[it] }
        digits[leftIdx]*10 + digits[rightIdx]
    }

    println(total)
}