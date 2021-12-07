package day07

import readInput
import result
import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val mov = input[0].split(",").filter { it.isNotBlank() }.map { it.toInt() }
        var min = Integer.MAX_VALUE
        for (i in 0..mov.size) {
            var c = 0
            mov.forEach {
                c += abs(i - it);
                if (c > min) return@forEach
            }
            min = if (c < min) c else min
        }
        return min
    }

    fun part2(input: List<String>): Int {
        val mov = input[0].split(",").filter { it.isNotBlank() }.map { it.toInt() }
        var min = Integer.MAX_VALUE
        for (i in 0..mov.size) {
            var c = 0
            mov.forEach {
                c += abs(i - it) + (abs(i - it) * abs(i - it))
                if (c > min) return@forEach
            }
            min = if (c < min) c else min
        }
        return min/2
    }

    val testInput = readInput("day07/Day07_test")
    result(part1(testInput), 37)
    result(part2(testInput), 168)

    val input = readInput("day07/Day07")
    result(part1(input), 340987)
    result(part2(input), 96987874)
}