package aoc2023.day02

import readInput
import result

fun main() {

    val testInput = readInput(2023,2, true)
    result(part1(testInput), 8)
    result(part2(testInput), 2286)

    val input = readInput(2023, 2)
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int {
    // line = Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    return input.mapIndexed { index, line ->
        if (splitGame(line).map { grab ->
                grab.map {
                    it[1] == "red" && it[0].toInt() <= 12 || it[1] == "green" && it[0].toInt() <= 13 || it[1] == "blue" && it[0].toInt() <= 14
                }.all { it }
            }.all { it }) {
            index + 1
        } else {
            0
        }
    }.sum()
}

fun part2(input: List<String>): Int {
    // line = Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    return input.mapIndexed { index, line ->
        val game = splitGame(line).map { show ->
            val res = show.foldIndexed(mutableMapOf("red" to 0, "blue" to 0, "green" to 0)) { index, acc, grab ->
                acc.compute(grab[1]) { _, v -> v?.plus(grab[0].toInt()) ?: grab[0].toInt() }
                acc
            }
            res
        }

        listOf("red", "blue", "green").map { color ->
            game.map { it[color]!! }.max()
        }.fold(1) { acc, i -> acc * i }
    }.sum()
}

fun splitGame(line: String): List<List<List<String>>> {
    return line.split(":")[1]
        .split(";")
        .asSequence()
        .map { it.split(",") }
        .map { it.map { it.trim() } }
        .map { it.map { it.split(" ") } }.toList()
}