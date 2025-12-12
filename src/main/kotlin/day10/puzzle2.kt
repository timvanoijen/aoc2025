package day10

import utils.readLines
import kotlin.collections.toMutableList
import kotlin.math.roundToInt

private const val DAY = 10

fun main() {
    val lines = readLines(DAY, test = false)

    val total = lines.sumOf { line ->

        // Parse input
        val buttons = line.substring(line.indexOf("]") + 1, line.indexOf("{"))
            .split(" ")
            .filter { it.isNotBlank() }
            .map { buttonString ->
                buttonString.substring(1, buttonString.length - 1).split(",").map { it.toInt() }
            }
        val joltages = line.substring(line.indexOf("{") + 1, line.length - 1).split(",").map { it.toInt() }

        // Define equations
        val equations = (0..<joltages.size).map { y ->
            val equation = (0..<buttons.size).map { x -> if (buttons[x].contains(y)) 1.0 else 0.0 }.toMutableList()
            equation.add(-joltages[y].toDouble())
            equation
        }.toMutableList()

        // Calculate variable limits
        val variableLimits = buttons.map { it.minOf { j -> joltages[j]} + 1}

        // Create Problem
        val s = Problem(equations, variableLimits)

        // Find solution with lowest sum
        s.iterateSolutions().minOf { it.sum() }
    }
    println(total)

}

data class Problem(
    val equations: List<List<Double>>,
    val variableLimits: List<Int>,
) {
    val nrVariables: Int get() = equations[0].size - 1

    fun iterateSolutions(): List<List<Int>> {
        val reduced = reduce()
        if (reduced != null) {
            val (reducedProblem, solutionMapping) = reduced
            return reducedProblem.iterateSolutions()
                .map { solutionMapping(it) }
                .filter { solution -> solution.all { it.isInt() } }
                .map { solution -> solution.map { it.roundToInt()} }
                .filter { solution -> solution.all { it >= 0 } }
        }

        val solutions = (0..<nrVariables).fold(listOf<List<Int>>(emptyList())) { ll, i ->
            ll.flatMap { l -> (0..<variableLimits[i]).map { l + it } }
        }

        return solutions.filter { solution ->
            equations.all { equation ->
                equation.zip(solution + 1) { a, b -> a * b.toDouble() }.sum().isZero()
            }
        }
    }

    fun reduce(): Pair<Problem, (List<Int>) -> List<Double>>? {
        for(i in equations.indices) {
            val equation = equations[i]

            // Find substitution
            val jSubs = (0..<nrVariables).firstOrNull { !equation[it].isZero() }
            if (jSubs == null) continue

            // Define new problem equations
            val newEquations = equations
                .filter { it != equation }
                .map { eq2 -> eq2.indices.filter { it != jSubs }.map {
                    j -> eq2[j] - eq2[jSubs] * (equation[j] / equation[jSubs]) }
                }
                .filter { eq2 -> eq2.any { !it.isZero()}}

            if (newEquations.isEmpty()) return null

            // Calculate new variableLimits
            val newVariableLimits = variableLimits.filterIndexed { j, _ -> j != jSubs }

            // Calculate solution mapping
            val solutionMapping = { newSolution: List<Int> ->
                (0..<nrVariables).map { j ->
                    if (j < jSubs) newSolution[j] else if (j > jSubs) newSolution[j - 1] else 0
                }.map(Int::toDouble).toMutableList().also { solution ->
                    solution[jSubs] = equation.indices.map { j ->
                        (-equation[j]) * (solution + 1.0)[j] / equation[jSubs]
                    }.reduce(Double::plus)
                }
            }

            return Problem(newEquations, newVariableLimits) to solutionMapping
        }
        return null
    }
}

private fun Double.isZero() = this in (-0.000000001 .. 0.000000001)

private fun Double.isInt() = (this - this.roundToInt()).isZero()