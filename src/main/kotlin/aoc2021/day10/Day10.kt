package aoc2021.day10

import readInput
import result
import resultLong

fun main() {
    result(part1(readInput(2021, "day10/Day10_test")), 26397)
    result(part1(readInput(2021, "day10/Day10")), 318081)

    resultLong(part2(readInput(2021, "day10/Day10_test")), 288957)
    resultLong(part2(readInput(2021, "day10/Day10")), 4361305341)
}

fun part1(input: List<String>): Int {
    return verifyLines(input)
        .filter { it.length == 1 }.sumOf {
            (when (it[0].toString()) {
                ")" -> 3
                "]" -> 57
                "}" -> 1197
                ">" -> 25137
                else -> 0
            }).toInt()
        }
}

fun part2(readInput: List<String>): Long {
    val lines = verifyLines(readInput)
        .filter { it.length != 1 }
        .map {
            it.reversed()
                .map { c ->
                    when (c.toString()) {
                        "(" -> 1L
                        "[" -> 2L
                        "{" -> 3L
                        "<" -> 4L
                        else -> 0L
                    }
                }.reduce { acc, l -> acc * 5L + l }
        }
        .sortedDescending()
    return lines[(lines.size / 2)]
}

fun verifyLines(input: List<String>): List<String> {
    val pairs = listOf(
        Pair("(", ")"),
        Pair("[", "]"),
        Pair("{", "}"),
        Pair("<", ">")
    ).map { (o, c) -> Pair(o.toCharArray()[0], c.toCharArray()[0]) }

    fun formatString(line: String): String {
        line.forEachIndexed { index, chr ->
            pairs.find { (_, c) ->
                chr == c
            }?.let {
                if (line[index - 1] == it.first) {
                    return formatString(line.removeRange(index - 1..index))
                }
                return it.second.toString()
            }
        }
        return line
    }

    return input.map { formatString(it) }
}

