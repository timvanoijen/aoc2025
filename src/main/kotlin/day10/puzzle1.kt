package day10

import utils.readLines
import java.util.PriorityQueue

private const val DAY = 10

fun main() {
    val lines = readLines(DAY, test = false)

    val total = lines.sumOf { line ->

        // Parse input
        val sol = line.substring(1, line.indexOf("]")).toCharArray().map { it == '#' }
        val buttons = line.substring(line.indexOf("]") + 1, line.indexOf("{"))
            .split(" ")
            .filter { it.isNotBlank() }
            .map { buttonString ->
                buttonString.substring(1, buttonString.length - 1).split(",").map { it.toInt() }
            }

        getMinSteps(sol, buttons)
    }
    println(total)
}

fun getMinSteps(sol: List<Boolean>, buttons: List<List<Int>>): Int {
    val priorityQueue = PriorityQueue<Pair<List<Boolean>, Int>>(compareBy { it.second })
    val visited = mutableSetOf<List<Boolean>>()

    priorityQueue.add(List(sol.size) { false } to 0)

    while(priorityQueue.isNotEmpty()) {
        val (s, nrSteps) = priorityQueue.poll()
        if (s == sol) return nrSteps

        buttons.forEach { b ->
            val s2 = s.toMutableList()
            b.forEach { s2[it] = !s2[it] }
            if (visited.add(s2)) priorityQueue.add(s2 to nrSteps + 1)
        }
    }
    throw IllegalStateException("No steps left in sol")
}