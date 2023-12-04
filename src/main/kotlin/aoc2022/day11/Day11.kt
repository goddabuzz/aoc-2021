package aoc2022.day11

import readInput
import result

fun main() {

    val testInput = readInput(2022,11, true).map { it.toInt() }
    result(part1(testInput), 10605)
    //result(part2(testInput), 0)

    val input = readInput(2022, 11).map { it.toInt() }
    println(part1(input))
    //println(part2(input))
}

fun part1(input: List<Int>): Int {
    return 0
}
fun part2(input: List<Int>): Int {
    return part1(input)
}

