package aoc2022.day03

import readInput
import result

fun main() {

    val testInput = readInput(2022, 3, true)
    result(part1(testInput), 157)
    result(part2(testInput), 70)

    val input = readInput(2022, 3)
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int = input.sumOf {
    val (left, right) = it.chunked(it.length / 2)
    val v = left.first { c -> right.contains(c) }
    v.code - (if (v.isLowerCase()) 96 else 38)
}

fun part2(input: List<String>): Int = input.windowed(3, 3)
    .map { it.first().first { c: Char -> it[1].contains(c) && it[2].contains(c) } }
    .sumOf { it.code - (if (it.isLowerCase()) 96 else 38); }