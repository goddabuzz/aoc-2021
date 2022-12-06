package aoc2021.day01

import readInput
import result

fun main() {

    fun part1(input: List<Int>): Int = input.zipWithNext { a, b -> a < b }.filter { it }.size
    fun part2(input: List<Int>): Int = part1(input.windowed(3) { it.sum() })

    val testInput = readInput(2021, 1).map { it.toInt() }
    result(part1(testInput), 7)
    result(part2(testInput), 5)

    val input = readInput(2021, 1).map { it.toInt() }
    result(part1(input), 1342)
    result(part2(input), 1378)
}