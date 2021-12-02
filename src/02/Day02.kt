package `02`

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val pairs = input.map {
            Pair(it.split(" ")[0], it.split(" ")[1].toInt())
        }

        val grouped = pairs.groupBy(keySelector = { it.first }, valueTransform = { it.second })

        val forward = grouped["forward"]!!.sum()
        val up = grouped["up"]!!.sum()
        val down = grouped["down"]!!.sum()

        return forward * (down - up)
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        for (line in input) {
            val splits = line.split(" ")
            val pair = Pair(splits[0], splits[1].toInt())

            when (pair.first) {
                "down" -> aim += pair.second
                "up" -> aim -= pair.second
                "forward" -> {
                    horizontal += pair.second
                    depth += aim * pair.second
                }
            }
        }

        return horizontal * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/02", "test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("src/02", "real")
    println(part1(input))
    println(part2(input))
}
