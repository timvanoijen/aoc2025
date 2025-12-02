package day2

import utils.readText
import java.lang.Long.parseLong

private const val DAY = 2

fun main() {
    val input = readText(DAY, test = false)

    val total =
        input.split(",").sumOf { range ->
        val (from, till) = range.split("-").map { it.toLong() }
        generateSequence(1) { it + 1 }
            .map { parseLong(it.toString() + it.toString()) }
            .dropWhile { it < from }
            .takeWhile { it <= till }
            .sum()
    }
    println(total)
}