package day4

import utils.readLines

private const val DAY = 4

fun main() {
    val square = readLines(DAY, test = false).map { it.toCharArray().toMutableList() }
        .toMutableList()

    val (h, w) = square.count() to square[0].count()
    var totalRemoved = 0

    while(true) {
        val removable = (0..<h).flatMap { y ->
            (0..<w)
                .filter { x -> square[y][x] == '@' }
                .filter { x ->
                    (-1..1).sumOf { dy ->
                        (-1..1).count { dx ->
                            y + dy in 0..<h && x + dx in 0..<w &&
                                    square[y + dy][x + dx] == '@'
                        }
                    } < 5
                }
                .map { x -> y to x }
        }
        if (removable.isEmpty()) break
        totalRemoved += removable.count()
        removable.forEach { (y, x) -> square[y][x] = '.' }
    }
    println(totalRemoved)
}