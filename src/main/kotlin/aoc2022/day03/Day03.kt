package aoc2022.day03

import readInput
import result

fun main() {

    val testInput = readInput(2022,"day03/Day03_test")
    result(part1(testInput), 157)

    val input = readInput(2022, "day03/Day03")
    result(part1(input), 7763)

    result(part2(testInput), 70)
    result(part2(input), 2569)
}


fun part1(input: List<String>): Int {
    return input.sumOf {
        val compartmentLength = it.length / 2
        val left = it.take(compartmentLength)
        val right = it.takeLast(compartmentLength)

        val v = left.first { c -> right.contains(c) }
        v.code - (if (v.isLowerCase()) 96 else 38)
    }
}
fun part2(input: List<String>): Int {
    return input.windowed(3, 3)
        .map { it.first().first { c: Char -> it[1].contains(c) && it[2].contains(c) } }
        .sumOf { it.code - (if (it.isLowerCase()) 96 else 38); }
}