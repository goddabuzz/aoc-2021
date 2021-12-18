package day14

import readInput
import result

fun main() {
    result(part1(readInput("Day14/Day14_test")), 1588)
    result(part1(readInput("Day14/Day14")), 2621)
}

fun part1(input: List<String>): Int {

    var template = input.first()
    val last = template.last()
    val rules = input.filter { "->" in it }.map { it.split(" -> ") }

    for (i in 0..9) {

        template = template.windowed(2).map { seq ->
            rules.forEach { rule ->
                if (rule[0][0] == seq[0] && rule[0][1] == seq[1]) {
                    return@map seq[0] + rule[1]
                }
            }
            seq[0]
        }.joinToString("") + last
        println(template)
    }

    val counts = template.groupBy { it }.map { entry -> entry.value.size }
    return counts.maxOf { it } - counts.minOf { it }
}
