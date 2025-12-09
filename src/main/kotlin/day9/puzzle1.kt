package day9

import utils.readLines
import kotlin.math.abs

private const val DAY = 9

fun main() {
    val t = readLines(DAY, test = false).map { it.split(",").map(String::toLong) }

    val total = t.flatMap { (x1, y1) ->
        t.map { (x2, y2) ->
            (abs(x1 - x2) + 1) * (abs(y1 - y2) + 1)
        }
    }.max()

    println(total)
}