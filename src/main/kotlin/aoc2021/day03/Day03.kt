package aoc2021.day03

import readInput
import result

fun main() {

    fun part1(input: List<String>): Int {
        val result = input[0].mapIndexed { index, _ ->
            val count = input.count { it[index].toString() == "0" }
            if (count > input.size / 2) "0" else "1"
        }

        val gamma = result.joinToString("") { if (it == "0") "1" else "0" }.toInt(2)
        val epsilon = result.joinToString("").toInt(2)
        return gamma * epsilon
    }

    fun calcOxygen(input: List<String>): Int {
        var result = input
        for (i in input[0].indices) {
            val size = result.size
            if (size == 1) break

            val c1 = result.count { it[i].toString() == "1" }
            val c1IsMostCommon = c1 >= size.toDouble() / 2

            result = result.filter {
                it[i].toString() == "1" && c1IsMostCommon || it[i].toString() == "0" && !c1IsMostCommon
            }
        }
        return result.last().toInt(2)
    }

    fun calcCo2(input: List<String>): Int {
        var result = input
        for (i in input[0].indices) {
            val size = result.size
            if (size == 1) break

            val c0 = result.count { it[i].toString() == "0" }
            val c0IsLeastCommon = c0 <= size.toDouble() / 2

            result = result.filter {
                it[i].toString() == "1" && !c0IsLeastCommon || it[i].toString() == "0" && c0IsLeastCommon
            }
        }
        return result.last().toInt(2)
    }

    fun part2(input: List<String>): Int {
        val oxygen = calcOxygen(input)
        val co2 = calcCo2(input)
        return oxygen * co2
    }

    val testInput = readInput(2021, "day03/Day03_test")
    result(part1(testInput), 198)
    result(part2(testInput), 230)

    val input = readInput(2021, "day03/Day03")
    result(part1(input), 693486)
    result(part2(input), 3379326)
}