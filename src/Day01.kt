fun main() {
    fun part1(input: List<String>): Int {
        val intArray = input.map { it.toInt() }.toIntArray()

        var increased = 0

        for (i in 1 until intArray.size) {
            if (intArray[i] > intArray[i - 1])
                increased++
        }


        return increased
    }

    fun part2(input: List<String>): Int {
        val intArray = input.map { it.toInt() }.toIntArray()

        var increased = 0

        for (i in 0..intArray.size - 4) {
            val firstSlidingWindow = intArray[i] + intArray[i + 1] + intArray[i + 2]
            val secondSlidingWindow = intArray[i + 1] + intArray[i + 2] + intArray[i + 3]

            if (secondSlidingWindow > firstSlidingWindow)
                increased++
        }

        return increased
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
