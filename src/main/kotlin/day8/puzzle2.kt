package day8

import utils.readLines

private const val DAY = 8

fun main() {
    val j = readLines(DAY, test = false).map { it.split(",").map(String::toLong) }

    val connToDistances = (0..<(j.size - 1)).flatMap { i1 ->
        (i1 + 1 ..< j.size).map { i2 ->
            val d = (0..<3).sumOf { (j[i1][it]-j[i2][it])*(j[i1][it]-j[i2][it]) }
            (i1 to i2) to d
        }
    }

    val circuits = mutableMapOf<Junction, Set<Junction>>()
    val finalConnToDistance = connToDistances
        .sortedBy { it.second }
        .asSequence()
        .onEach { (c, _) ->
            val m1 = circuits[c.first] ?: setOf(c.first)
            val m2 = circuits[c.second] ?: setOf(c.second)
            val merged = m1 + m2
            merged.forEach { circuits[it] = merged }
        }
        .first { circuits[0]?.size == j.size}

    val (i1Last, i2Last) = finalConnToDistance.first
    println(j[i1Last][0] * j[i2Last][0])
}