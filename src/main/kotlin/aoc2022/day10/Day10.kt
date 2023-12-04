package aoc2022.day10

import readInput
import result

fun main() {

    val testInput = readInput(2022,10, true).map { it.split(" ")}
    result(part1(testInput), 13140)
    result(part2(readInput(2022,10, true)), 0)

    val input = readInput(2022, 10).map { it.split(" ")}
    println(part1(input))
    println(part2(readInput(2022, 10)))
}

fun part1(input: List<List<String>>): Int {
    var x = 1
    var cycles = 0
    var total = 0;

    input.forEach {
        cycles++
        if (cycles in listOf(20, 60, 100, 140, 180, 220)) {
            total += x * cycles
        }
        if (it[0] != "noop") {
            cycles++
            if (cycles in listOf(20, 60, 100, 140, 180, 220)) {
                total += x * cycles
            }
            x += it[1].toInt()
        }
    }
    return total
}

fun part2(input: List<String>): Int {
    var numberOfCycles = 0
    val screen = List(6) {
        MutableList(40) {
            '.'
        }
    }
    var spritePosition = 1
    parseInput(input).forEach {
        repeat(it.cycles) {
            numberOfCycles++
            val crtIndex = (numberOfCycles - 1) % 40
            screen[(numberOfCycles - 1) / 40][crtIndex] = if (crtIndex in spritePosition - 1..spritePosition + 1) {
                '#'
            } else {
                '.'
            }
        }
        spritePosition += it.value
    }
    screen.forEach {
        println(it.joinToString(" "))
    }
    return 0
}

fun parseInput(lines: List<String>): List<Command> {
    return lines.map { line ->
        line.split(" ").run {
            when (this[0]) {
                "addx" -> {
                    Command(this[1].toInt(), 2)
                }

                else -> {
                    Command(0, 1)
                }
            }
        }
    }
}

data class Command(val value: Int, val cycles: Int)
