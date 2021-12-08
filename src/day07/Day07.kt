package day07

import readInput
import result
import kotlin.math.abs

fun main() {

    fun part1(input: List<Int>): Int {
        var min = Integer.MAX_VALUE
        for (i in 0..input.size) {
            var c = 0
            input.forEach {
                c += abs(i - it);
                if (c > min) return@forEach
            }
            min = if (c < min) c else min
        }
        return min
    }

    fun part2(input: List<Int>): Int {
        var min = Integer.MAX_VALUE
        for (i in 0..input.size) {
            var c = 0
            input.forEach {
                val n = abs(i - it);
                c += n + n * n
                if (c > min) return@forEach
            }
            min = if (c < min) c else min
        }
        return min / 2
    }

    val testInput = readInput("day07/Day07_test")[0].split(",").map { it.toInt() }
    result(part1(testInput), 37)
    result(part2(testInput), 168)

    val input = readInput("day07/Day07")[0].split(",").map { it.toInt() }
    result(part1(input), 340987)
    result(part2(input), 96987874)
}