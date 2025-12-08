package day8

import utils.readLines

private const val DAY = 8

typealias Junction = Int

fun main() {
    val test = true
    val nr = if (test) 10 else 1000
    val j = readLines(DAY, test = test).map { it.split(",").map(String::toLong) }

    val connToDistances = (0..<(j.size - 1)).flatMap { i1 ->
        (i1 + 1 ..< j.size).map { i2 ->
            val d = (0..<3).sumOf { (j[i1][it]-j[i2][it])*(j[i1][it]-j[i2][it]) }
            (i1 to i2) to d
        }
    }

    val circuits = mutableMapOf<Junction, Set<Junction>>()
    connToDistances
        .sortedBy { it.second }
        .asSequence()
        .onEach { (c, _) ->
            val m1 = circuits[c.first] ?: setOf(c.first)
            val m2 = circuits[c.second] ?: setOf(c.second)
            val merged = m1 + m2
            merged.forEach { circuits[it] = merged }
        }
        .take(nr).toList()


    val total = circuits.values.distinct()
        .map {it.size.toLong()}
        .sortedByDescending { it }
        .take(3)
        .reduce(Long::times)

    println(total)
}