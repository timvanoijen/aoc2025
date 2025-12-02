package utils

import kotlin.math.abs
import kotlin.math.log10

fun Int.length() = toLong().length()

fun Long.length() = when(this) {
    0L -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun nToInf(n: Int) = generateSequence(n) { it + 1 }

fun oneToInf() = nToInf(1)

fun zeroToInf() = nToInf(0)

