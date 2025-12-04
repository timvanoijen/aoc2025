package day4

import utils.readLines

private const val DAY = 4

fun main() {
    val square = readLines(DAY, test = false).map { it.toCharArray().toList() }

    val (h, w) = square.count() to square[0].count()

    val total =
        (0..<h).sumOf { y ->
            (0..<w)
                .filter { x -> square[y][x] == '@'}
                .map { x ->
                    val cnt = (-1..1).sumOf { dy ->
                        (-1..1).count { dx ->
                            y + dy in 0..<h && x + dx in 0..<w &&
                                    square[y + dy][x + dx] == '@'
                        }
                    }
                    println("$x,$y: $cnt")
                    cnt
                }
                .count { cnt -> cnt < 5 }
        }
    println(total)
}