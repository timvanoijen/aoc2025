package utils

import java.io.InputStreamReader

fun readText(day: Int, test: Boolean = false): String {
    return read(day, test).readText()
}

fun readLines(day: Int, test: Boolean = false): List<String> {
    return read(day, test).readLines()
}

private fun read(day: Int, test: Boolean = false): InputStreamReader {
    val fileName = if (test) "test.txt" else "input.txt"
    val path = "/day$day/$fileName"

    return object {}.javaClass.getResourceAsStream(path)?.reader() ?: throw IllegalArgumentException("$path does not exist")
}