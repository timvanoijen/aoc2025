package day7

import utils.readLines

private const val DAY = 7

fun main() {
    val f = readLines(DAY, test = false).map { it.toCharArray() }
    val (h, w) = f.size to f[0].size
    val (yS, xS) = 0 to f[0].indexOf('S')

    val nrTimelines: MutableMap<Pair<Int, Int>, Long> = mutableMapOf()
    nrTimelines[yS to xS] = 1
    (1..<h).forEach { y ->
        (0..<w).forEach { x ->
            if (f[y][x] == '.') {
                var n = nrTimelines[y - 1 to x] ?: 0
                if (x > 0 && f[y][x - 1] == '^') n += nrTimelines[y - 1 to x - 1] ?: 0
                if (x < w - 1 && f[y][x + 1] == '^') n += nrTimelines[y - 1 to x + 1] ?: 0
                nrTimelines[y to x] = n
            }
        }
    }
    val total = (0..<w).sumOf { x ->  nrTimelines[ h - 1 to x] ?: 0 }
    println(total)
}