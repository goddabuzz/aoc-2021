package aoc2022.day05

import readInput
import result

fun main() {

    val testInput = readInput(2022, "day05/Day05_test")
    result(part1(testInput), "CMZ")
    result(part2(testInput), "MCD")

    val input = readInput(2022, "day05/Day05")
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>, topFirst : Boolean = true): String {
    val stack = input.take(input.indexOf("") - 1).map { s -> s.chunked(4) }
    var st = mutableListOf<MutableList<String>>()

    // Create stacks
    for (i in 0 until stack.maxOf { it.size }) st.add(mutableListOf())
    stack.forEach { it.mapIndexed { index, s -> st[index].add(s[1].toString()) } }
    st = st.map { strings -> strings.filterNot { it.isBlank() } } as MutableList<MutableList<String>>

    // Movement
    input.subList(stack.size + 2, input.size)
        .map {
            it.replace("move", "")
                .replace("from", "")
                .replace("to", "")
                .split(" ")
                .filterNot { s -> s.isBlank() }
                .map { s -> s.toInt() }
        }.forEach {
            val from = st[it[1]-1]
            var move = st[it[1]-1].take(it[0])
            // part 2
            move = if (topFirst) move.reversed() else move

            val to = st[it[2]-1]
            to.addAll(0, move)
            repeat(move.size) { from.removeAt(0) }
        }
    return st.joinToString("") { it[0] }
}

fun part2(input: List<String>): String {
    return part1(input, false)
}