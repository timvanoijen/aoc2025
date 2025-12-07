package day7

import utils.readLines

private const val DAY = 7

fun main() {
    val f = readLines(DAY, test = false).map { it.toCharArray() }
    val (h, w) = f.size to f[0].size

    val (yS, xS) = 0 to f[0].indexOf('S')
    val visited = mutableSetOf<Pair<Int, Int>>()

    var nrSplits = 0
    val s = ArrayDeque(listOf(yS to xS))
    while(s.isNotEmpty()) {
        val (y, x) = s.removeLast().also { visited.add(it) }
        if (y !in (0..<h) || x !in (0..<w)) continue

        val newPos = when(f[y][x]) {
            '^' -> listOf(y + 1 to x - 1, y + 1 to x + 1).also { nrSplits++ }
            else -> listOf(y + 1 to x)
        }
        newPos.filter { it !in visited}.forEach{ s.addLast(it) }
    }

    println(nrSplits)
}