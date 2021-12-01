package day01

import readInput
import result

fun main() {

    fun part1(input: List<Int>): Int = input.zipWithNext { a, b -> a < b }.filter { it }.size

    fun part2(input: List<Int>): Int = part1(input.mapIndexedNotNull { index, i ->
        if (index < input.size - 2) {
            i + input[index + 1] + input[index + 2]
        } else null
    })

    val testInput = readInput("day01/Day01_test").map { it.toInt() }
    result(part1(testInput), 7)
    result(part2(testInput), 5)

    val input = readInput("day01/Day01").map { it.toInt() }
    result(part1(input), 1342)
    result(part2(input), 1378)
}