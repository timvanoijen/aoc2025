package day2

import utils.length
import utils.oneToInf
import utils.readText
import java.lang.Long.parseLong

private const val DAY = 2

fun main() {
    val input = readText(DAY, test = false)

    val total =
        input.split(",").sumOf { range ->
            val (from, till) = range.split("-").map { it.toLong() }

            (2..till.length()).flatMap { nrReps ->
                oneToInf()
                    .map { parseLong(it.toString().repeat(nrReps)) }
                    .dropWhile { it < from }
                    .takeWhile { it <= till }
            }
            .distinct()
            .sum()
        }
    println(total)
}