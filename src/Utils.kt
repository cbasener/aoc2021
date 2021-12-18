import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Reads lines from the given input txt file.
 */
fun readInput(parent: String, name: String) = File(parent, "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)


// Day3

fun getNthColumn(input: List<String>, nth: Int): String {
    var column = ""

    for (line in input) {
        column += line.chunked(1)[nth]
    }

    return column
}

fun getMostAndLeastCommon(input: String): Pair<Int, Int>? {
    val zeroCount = input.count { it == '0' }
    val oneCount = input.count { it == '1' }

    return if (zeroCount > oneCount) {
        Pair(0, 1)
    } else if (oneCount > zeroCount) {
        Pair(1, 0)
    } else {
        return null
    }
}

fun filterLines(input: List<String>, filter: Int, columnIndex: Int): MutableList<String> {
    return input.filter { line -> line.chunked(1)[columnIndex].toInt() == filter }.toMutableList()
}

// Day4

class Bingo(private val board: Array<Array<Pair<Int, Boolean>>>) {
    fun print() {
        for (i in 0..4) {
            for (j in 0..4) {
                val value = board[i][j]

                val output = if (value.second) value.first.toString() + "*" else value.first.toString()
                print("$output ")
            }
            println()
        }
    }


    fun markNumber(number: Int) {
        for (i in 0..4) {
            for (j in 0..4) {
                if (board[i][j].first == number) {
                    board[i][j] = Pair(number, true)
                }
            }
        }
    }

    private fun getRow(rowIndex: Int): Array<Pair<Int, Boolean>> {
        check(rowIndex in 0..4)
        return board[rowIndex]
    }

    private fun getColumn(colIndex: Int): Array<Pair<Int, Boolean>> {
        check(colIndex in 0..4)

        return arrayOf(
            board[0][colIndex],
            board[1][colIndex],
            board[2][colIndex],
            board[3][colIndex],
            board[4][colIndex]
        )
    }

    private fun isWinningSequence(sequence: Array<Pair<Int, Boolean>>): Boolean {
        return sequence.map { it.second }.all { b -> b }
    }

    fun isWinningBoard(): Boolean {
        val hasWinningRow =
            isWinningSequence(getRow(0)) || isWinningSequence(getRow(1)) || isWinningSequence(getRow(2)) ||
                    isWinningSequence(getRow(3)) || isWinningSequence(getRow(4))

        val hasWinningColumn = isWinningSequence(getColumn(0)) || isWinningSequence(getColumn(1)) ||
                isWinningSequence(getColumn(2)) || isWinningSequence(getColumn(3)) || isWinningSequence(getColumn(4))

        return hasWinningRow || hasWinningColumn
    }

    fun getUnmarked(): List<Int> {
        return listOf(
            getUnmarkedValue(getRow(0)),
            getUnmarkedValue(getRow(1)),
            getUnmarkedValue(getRow(2)),
            getUnmarkedValue(getRow(3)),
            getUnmarkedValue(getRow(4))
        )
    }

    private fun getUnmarkedValue(array: Array<Pair<Int, Boolean>>): Int {
        return array.filter { !it.second }.sumOf { it.first }
    }
}

fun createBingo(input: List<String>): Bingo {
    check(input.size == 5)
//    println(input)

    val regex = "\\s+".toRegex()

    val row1 = input[0].trim().split(regex).map { Pair(it.toInt(), false) }.toTypedArray()
    val row2 = input[1].trim().split(regex).map { Pair(it.toInt(), false) }.toTypedArray()
    val row3 = input[2].trim().split(regex).map { Pair(it.toInt(), false) }.toTypedArray()
    val row4 = input[3].trim().split(regex).map { Pair(it.toInt(), false) }.toTypedArray()
    val row5 = input[4].trim().split(regex).map { Pair(it.toInt(), false) }.toTypedArray()

    return Bingo(arrayOf(row1, row2, row3, row4, row5))
}

// Day5
data class Line(val start: Pair<Int, Int>, val end: Pair<Int, Int>)

fun createLine(input: String): Line {
    val splits = input.replace(" -> ", ",").split(",").map { it.toInt() }
    return Line(Pair(splits[0], splits[1]), Pair(splits[2], splits[3]))
}

private infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}

fun getAllLinePoints(line: Line): List<Pair<Int, Int>> {
    println("line: $line")

    if (line.start == line.end) {
        println("point")
        return listOf(line.start)
    } else {
        val points = mutableListOf<Pair<Int, Int>>()

        when {
            (line.start.first == line.end.first) -> {
                println("vertical line")

                for (i in line.start.second toward line.end.second) {
                    points.add(Pair(line.start.first, i))
                }
            }

            (line.start.second == line.end.second) -> {
                println("horizontal line")

                for (i in line.start.first toward line.end.first) {
                    points.add(Pair(i, line.start.second))
                }
            }
            else -> {
                println("diagonal line")

                for ((x, y) in (line.start.first toward line.end.first).zip(
                    line.start.second toward line.end.second
                )) {
                    points.add(Pair(x, y))
                }
            }
        }

        println("points: $points")

        return points
    }
}

// Day 6

// Day 10

fun getFirstIllegalCharacter(input: String): Optional<Char> {
    val stack = Stack<Char>()

    val closingToOpening = mapOf('}' to '{', ']' to '[', ')' to '(', '>' to '<')

    for (current in input) {
        if (isOpeningCharacter(current)) {
            stack.push(current)
        } else {
            // closing character found -> check if it is matching
            if (closingToOpening[current] == stack.peek()) {
                // matching
                stack.pop()
            } else {
                return Optional.of(current)
            }
        }
    }

    return Optional.empty()
}

fun isOpeningCharacter(input: Char): Boolean = "{[(<".contains(input)
//fun isClosingCharacter(input: Char): Boolean = "}])>".contains(input)

// Day 14
fun day14Readinput(parent: String, name: String): Pair<String, Map<String, String>> {
    TODO()
}

// Day 15

fun readInputAs2dArray(parent: String, name: String): Array<Array<Int>> {
    val lines = File(parent, "$name.txt").readLines()

    fun lineToArray(line: String): Array<Int> {
        val splits = line.chunked(1)
        return Array(line.length) { i -> splits[i].toInt() }
    }

    return Array(lines.size) { i -> lineToArray(lines[i]) }
}
