package day1

import utils.readLines
import java.lang.Integer.parseInt
import java.lang.Math.floorDiv
import java.lang.Math.floorMod
import kotlin.math.abs

private const val DAY = 1

fun main() {
    val lines = readLines(DAY, test = false)

    val total = lines.fold(50 to 0) { (pos, cnt), op ->
        val shift = parseInt(op.substring(1)) * (if (op[0] == 'L') -1 else 1)
        val posNew = floorMod(pos + shift, 100)
        val shiftNorm = abs(shift)
        val posNorm = if (shift < 0) floorMod(-pos, 100) else pos
        val cntNew = cnt + floorDiv(posNorm + shiftNorm, 100)
        posNew to cntNew
    }
    println(total)
}