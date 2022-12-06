package aoc2022.day04

import readInput
import result

fun main() {

    val testInput = readInput(2022,4, true)

    result(part1(testInput), 2)
    result(part2(testInput), 4)

    val input = readInput(2022, 4)
    println(part1(input))
    println(part2(input))
}
fun range(input: List<String>): List<List<IntRange>> = input.map { range ->
    range.split(",").map {
        val (x, y) = it.split("-").map { s -> s.toInt() }
        x..y
    }
}
fun part1(input: List<String>): Int = range(input).count {(x, y) ->
    x.contains(y.first) && x.contains(y.last) || y.contains(x.first) && y.contains(x.last)
}

fun part2(input: List<String>): Int = range(input).count { (x, y) ->
    x.contains(y.first) || x.contains(y.last) || y.contains(x.first) || y.contains(x.last)
}