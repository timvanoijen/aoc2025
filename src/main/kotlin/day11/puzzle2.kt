package day11

import utils.readLines
import java.util.PriorityQueue

private const val DAY = 11

fun main() {
    val lines = readLines(DAY, test = false)

    val nb = lines.associate { l ->
        val i = l.substring(0, l.indexOf(":"))
        val o = l.substring(l.indexOf(":") + 1).split(" ").filter { it.isNotBlank() }
        i to o
    }

    val initialState = State("svr", dac = false, fft = false)
    val cache = mutableMapOf<State, Long>()
    println(getNrPaths2(initialState, nb, cache))
}

fun getNrPaths2(state: State, nb: Map<String, List<String>>, cache: MutableMap<State, Long>): Long {
    if (state.s == "out") return if (state.dac && state.fft) 1L else 0L

    return cache[state] ?: (
        nb[state.s]!!.sumOf { s ->
            val newState = State(s, state.dac || state.s == "dac", state.fft || state.s == "fft")
            getNrPaths2(newState, nb, cache)
        }.also { cache[state] = it }
    )
}

data class State(val s: String, val dac: Boolean, val fft: Boolean)