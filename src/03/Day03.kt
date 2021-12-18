package `03`

import filterLines
import getMostAndLeastCommon
import getNthColumn
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val inputLength = input[0].length

        var gammaRateAsString = ""
        var epsilonRateAsString = ""

        for (i in 0 until inputLength) {
            val column = getNthColumn(input, i)

            val (mostCommon, leastCommon) = getMostAndLeastCommon(column)!!

            gammaRateAsString += mostCommon
            epsilonRateAsString += leastCommon
        }

        val gammaRate = Integer.parseInt(gammaRateAsString, 2)
        val epsilonRate = Integer.parseInt(epsilonRateAsString, 2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        val inputLength = input[0].length

        // oxygen
        var oxygenDiagnosticReport = input.toMutableList()

        for (i in 0 until inputLength) {
            if (oxygenDiagnosticReport.size == 1)
                continue

            val column = getNthColumn(oxygenDiagnosticReport, i)
            val mostAndLeastCommon = getMostAndLeastCommon(column)
            val mostCommon = mostAndLeastCommon?.first ?: 1

            oxygenDiagnosticReport = filterLines(oxygenDiagnosticReport, mostCommon, i)
        }

        val oxygenRating = oxygenDiagnosticReport.first()

        // CO2
        var co2DiagnosticReport = input.toMutableList()

        for (i in 0 until inputLength) {
            if (co2DiagnosticReport.size == 1)
                continue

            val column = getNthColumn(co2DiagnosticReport, i)
            val mostAndLeastCommon = getMostAndLeastCommon(column)
            val leastCommon = mostAndLeastCommon?.second ?: 0

            co2DiagnosticReport = filterLines(co2DiagnosticReport, leastCommon, i)
        }

        val co2Rating = co2DiagnosticReport.first()

        return Integer.parseInt(oxygenRating, 2) * Integer.parseInt(co2Rating, 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/03", "test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("src/03", "real")
    println(part1(input))
    println(part2(input))
}
