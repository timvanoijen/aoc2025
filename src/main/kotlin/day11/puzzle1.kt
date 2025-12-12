package day11

import utils.readLines
import java.util.PriorityQueue

private const val DAY = 11

fun main() {
    val lines = readLines(DAY, test = false)

    val nb = lines.associate { l ->
        val i = l.substring(0, l.indexOf(":"))
        val o = l.substring(l.indexOf(":") + 1).split(" ").filter { it.isNotBlank() }
        i to o
    }

    println(getNrPaths("you", nb))
}

fun getNrPaths(s: String, nb: Map<String, List<String>>): Long {
    if (s == "out") return 1L
    return nb[s]!!.sumOf { getNrPaths(it, nb) }
}