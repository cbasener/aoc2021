package `01`

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.windowed(2) { it[0] < it[1] }.count { it }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toInt() }.windowed(3) { it[0] + it[1] + it[2] }.windowed(2) { it[0] < it[1] }.count { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/01", "test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("src/01", "real")
    println(part1(input))
    println(part2(input))
}
