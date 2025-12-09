package day9

import utils.readLines
import java.lang.Math.floorMod
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private const val DAY = 9

fun main() {
    val t = readLines(DAY, test = false).map { it.split(",").map(String::toLong) }

    val polygon = t.mapIndexed { i, ti ->
        Line(
            x1 = t[floorMod((i - 1), t.size)][0],
            y1 = t[floorMod((i - 1), t.size)][1],
            x2 = ti[0],
            y2 = ti[1],
    ) }

    val rectangles = t.flatMap { (x1, y1) ->
        t.map { (x2, y2) -> Rect(x1, y1, x2, y2) }
    }

    val xRelevant = polygon.flatMap { listOf(it.x1 - 1, it.x1 + 1)}
    val yRelevant = polygon.flatMap { listOf(it.y1 - 1, it.y1 + 1)}

    val largestRect = rectangles
        .sortedByDescending { it.area }
        .first { rect ->
            xRelevant
                .filter { x -> x in (min(rect.x1, rect.x2)..max(rect.x1, rect.x2)) }
                .all { x -> Point(x, rect.y1).inPolygon(polygon) && Point(x, rect.y2).inPolygon(polygon) } &&
            yRelevant
                .filter { y -> y in (min(rect.y1, rect.y2)..max(rect.y1, rect.y2)) }
                .all { y -> Point(rect.x1, y).inPolygon(polygon) && Point(rect.x2, y).inPolygon(polygon) }
        }

    println(largestRect.area)
}

data class Rect(val x1: Long, val y1: Long, val x2: Long, val y2: Long) {
    val area: Long get() = (abs(x2 - x1) + 1) * (abs(y2 - y1) + 1)
}

data class Line(val x1: Long, val y1: Long, val x2: Long, val y2: Long)

data class Point(val x: Long, val y: Long) {
    fun inPolygon(lines: List<Line>): Boolean {
        if (lines.any(this::onLine)) return true
        val crossings = lines
            .filter { x in (min(it.x1, it.x2)..<max(it.x1, it.x2)) }
            .count { y > it.y1 }
        return crossings % 2 == 1
    }

    fun onLine(line: Line) =
        y in (min(line.y1, line.y2) .. max(line.y1, line.y2)) &&
        x in (min(line.x1, line.x2) .. max(line.x1, line.x2))
}