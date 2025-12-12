package day12

import utils.readLines

private const val DAY = 12

fun main() {
    val lines = readLines(DAY, test = false)

    val counts = lines.take(30).chunked(5).map { chunk ->
        chunk.drop(1).take(3).sumOf { row -> row.count { it == '#' } }
    }

    val total = lines.drop(30).sumOf { l ->

        val grid = l.substring(0, l.indexOf(':')).split("x").map(String::toInt)
        val numbers = l.substring(l.indexOf(':') + 1).split(" ").filter(String::isNotBlank).map(String::toInt)
        val totalBlocks = numbers.zip(counts) { a, b -> a * b }.sum()
        val gridArea = grid[0] * grid[1]
        if(gridArea - totalBlocks > 0) 1 else 0
    }
    println(total)
}