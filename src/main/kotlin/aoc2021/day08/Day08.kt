package aoc2021.day08

import readInput
import result

fun main() {

    fun part1(input: List<String>): Int {
        return input.map { it.split(" | ").last() }
            .flatMap { it.split(" ") }
            .count { it.length in listOf(2, 3, 4, 7) }
    }


    val testInput = readInput(2021, "day08/Day08_test")
    result(part1(testInput), 26)

    val input = readInput(2021, "day08/Day08")
    result(part1(input), 294)
}