package day12

import readInput
import result

fun main() {
    result(part1(readInput("Day12/Day12_test")), 226)
    result(part1(readInput("Day12/Day12")), 5178)
}

fun part1(input: List<String>): Int {
    val map = input.map { it.split("-") }
        .flatMap {
            listOf(
                it.first() to it.last(),
                it.last() to it.first()
            )
        }
        .groupBy({ it.first }, { it.second })

    fun findDistinctPath(
        path: List<String> = listOf("start")
    ): List<List<String>> {
        return if (path.last() == "end") {
            listOf(path)
        } else {
            map.getValue(path.last())
                .filter { it.all { c -> c.isUpperCase() } || !path.contains(it) }
                .flatMap {
                    findDistinctPath(path + it)
                }
        }
    }

    return findDistinctPath().size
}