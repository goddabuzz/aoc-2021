package day02

import readInput
import result

fun main() {

    fun part1(input: List<String>): Int {
        val forward = input.filter { it.contains("forward") }.sumOf { it.removePrefix("forward ").toInt() }
        val down = input.filter { it.contains("down") }.sumOf { it.removePrefix("down ").toInt() }
        val up = input.filter { it.contains("up") }.sumOf { it.removePrefix("up ").toInt() }

        return (down - up) * forward
    }

    fun part2(input: List<String>): Int {
        var forward = 0
        var aim = 0
        var depth = 0;

        input.forEach { s ->
            when (s.first().toString()) {
                "u" -> {
                    aim -= s.removePrefix("up ").toInt()
                }
                "d" -> {
                    aim += s.removePrefix("down ").toInt()
                }
                "f" -> {
                    forward += s.removePrefix("forward ").toInt()
                    depth += s.removePrefix("forward ").toInt() * aim
                }
            }
        }
        return depth * forward
    }

    val testInput = readInput("day02/Day02_test")
    result(part1(testInput), 150)
    result(part2(testInput), 900)

    val input = readInput("day02/Day02")
    result(part1(input), 1728414)
    result(part2(input), 1378)
}