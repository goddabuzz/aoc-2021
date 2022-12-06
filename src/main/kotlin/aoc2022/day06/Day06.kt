package aoc2022.day06

import readInput
import result

fun main() {

    val testInput = readInput(2022,6, true)
    result(part1(testInput), 7)
    result(part2(testInput), 19)

    val input = readInput(2022, 6)
    println(part1(input))
    println(part2(input))
}

fun validate(input: List<String>, length: Int): Int = input[0].windowed(length)
    .indexOfFirst {
        it.sumOf { c: Char -> it.count {
                s -> c == s}
        } == length
    } + length

fun part1(input: List<String>) = validate(input, 4)
fun part2(input: List<String>) = validate(input, 14)