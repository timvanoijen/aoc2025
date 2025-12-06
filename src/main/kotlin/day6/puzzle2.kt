package day6

import utils.readLines

private const val DAY = 6

fun main() {
    val lines = readLines(DAY, test = false)
    val ops = lines.last().split(" ").filter(String::isNotBlank)

    val (h, w) = lines.count() - 1 to lines.maxOf { it.length }
    val numbers = (0..<w).map { x ->
        (0..<h).map { y ->
            lines[y].getOrElse(x) { ' ' }
        }.joinToString("").trim()
    }.chunkBy { it.isBlank() }

    val total = (0..<ops.size).sumOf { x ->
        val op: (Long, Long) -> Long = if (ops[x] == "*") Long::times else Long::plus
        numbers[x].map(String::toLong).reduce(op)
    }

    println(total)
}

fun <T>Iterable<T>.chunkBy(predicate: (T) -> Boolean): List<List<T>> {
    val result = mutableListOf<MutableList<T>>()
    var cur = mutableListOf<T>()
    forEach {
        if (predicate(it)) {
            result.add(cur)
            cur = mutableListOf()
        } else cur.add(it)
    }
    if (cur.isNotEmpty()) result.add(cur)
    return result
}