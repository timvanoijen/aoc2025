package day1

import utils.readLines
import java.lang.Integer.parseInt

private const val DAY = 1

fun main() {
    val lines = readLines(DAY, test = false)

    val locs = lines.runningFold(50) { acc, op ->
        val shift = parseInt(op.substring(1)) * (if (op[0] == 'L') -1 else 1)
        (acc + shift) % 100
    }
    println(locs.count { it == 0 })
}