package aoc2021.day02

import readInput
import result

fun main() {

    fun part1(input: List<String>): Int {
        val groups = input
            .map {
                val (a, b) = it.split(" ")
                Pair(a, b.toInt())
            }
            .groupBy { it.first }
            .mapValues { it.value.map { pair -> pair.second } }
        return (groups["down"]!!.sum() - groups["up"]!!.sum()) * groups["forward"]!!.sum()
    }

    fun part2(input: List<String>): Int {
        var forward = 0
        var aim = 0
        var depth = 0;

        input.map { it.split(" ") }
            .map { Pair(it[0], it[1].toInt()) }
            .forEach {
                when (it.first) {
                    "up" -> aim -= it.second
                    "down" -> aim += it.second
                    "forward" -> {
                        forward += it.second
                        depth += it.second * aim
                    }
                }
            }
        return depth * forward
    }

    val testInput = readInput(2021, "day02/Day02_test")
    result(part1(testInput), 150)
    result(part2(testInput), 900)

    val input = readInput(2021, "day02/Day02")
    result(part1(input), 1728414)
    result(part2(input), 1765720035)
}