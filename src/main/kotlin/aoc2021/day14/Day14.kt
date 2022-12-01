package aoc2021.day14

import readInput
import result
import resultLong

fun main() {
    result(part1(readInput(2021, "Day14/Day14_test")), 1588)
    result(part1(readInput(2021, "Day14/Day14")), 2621)

    resultLong(part2(readInput(2021, "Day14/Day14_test")), 2188189693529)
    resultLong(part2(readInput(2021, "Day14/Day14")), 2843834241366)
}

fun part1(input: List<String>): Int {

    var template = input.first()
    val last = template.last()
    val rules = input.filter { "->" in it }
        .map { it.split(" -> ") }

    repeat(10) {
        template = template.windowed(2).map { seq ->
            rules.forEach { rule ->
                if (rule[0][0] == seq[0] && rule[0][1] == seq[1]) {
                    return@map seq[0] + rule[1]
                }
            }
            seq[0]
        }.joinToString("") + last
    }

    val counts = template.groupBy { it }.map { entry -> entry.value.size }
    return counts.maxOf { it } - counts.minOf { it }
}

fun part2(input: List<String>): Long {

    val pairs = mutableMapOf<String, Long>()
    val template = input[0]
    val rules = input.filter { " -> " in it }
        .map { it.split(" -> ") }
        .groupBy { it[0] }
        .mapValues { it.value[0][1] }

    template.windowed(2).forEach {
        pairs[it] = pairs.getOrDefault(it, 0) + 1
    }

    repeat(40) {
        val newPairs = mutableMapOf<String, Long>()
        for ((key, value) in pairs) {
            if (key in rules) {
                val leftPair = key[0] + rules[key]!!
                val rightPair = rules[key]!! + key[1]

                newPairs[leftPair] = newPairs.getOrDefault(leftPair, 0) + value
                newPairs[rightPair] = newPairs.getOrDefault(rightPair, 0) + value

                pairs[key] = 0
            }
        }

        for ((key, value) in newPairs) {
            pairs[key] = pairs.getOrDefault(key, 0) + value
        }
    }

    val elemFreq = mutableMapOf<Char, Long>()
    elemFreq[template.last()] = 1
    pairs.forEach { (key, value) ->
        elemFreq[key[0]] = elemFreq.getOrDefault(key[0], 0) + value
    }
    return elemFreq.maxOf { it.value } - elemFreq.minOf { it.value }
}