package aoc2022.day08

import readInput
import result

fun main() {

    val testInput = map(readInput(2022, 8, true))
    result(part1(testInput), 21)
    result(part2(testInput), 8)

    val input = map(readInput(2022, 8))
    println(part1(input))
    println(part2(input))
}

fun map(input: List<String>): List<List<Int>> = input.filter { it.isNotBlank() }.map { it.map { i -> i.digitToInt() } }

fun sight(left: List<Int>, right: List<Int>, validate: Int): Boolean =
    left.all { it < validate } || right.all { it < validate }

fun part1(input: List<List<Int>>): Int {
    var count = 0;
    for (y in 1 until input.size - 1) {
        for (x in 1 until input[0].size - 1) {
            val v = input[y][x]
            val left = input[y].subList(0, x)
            val right = input[y].subList(x + 1, input[y].size)

            val column = input.map { it[x] }
            val top = column.subList(0, y)
            val bottom = column.subList(y + 1, column.size)

            if (sight(left, right, v) || sight(top, bottom, v)) {
                count++
            }
        }
    }
    return count + input.size * 2 + (input[0].size - 2) * 2
}

fun score(view: List<Int>, validate: Int): Int {
    view.forEachIndexed { index, i ->
        if (i >= validate) {
            return index+1
        }
    }
    return view.size
}

fun part2(input: List<List<Int>>): Int {
    val scores = mutableListOf<Int>();
    for (y in 1 until input.size - 1) {
        for (x in 1 until input[0].size - 1) {
            val v = input[y][x]
            val left = input[y].subList(0, x).reversed()
            val right = input[y].subList(x + 1, input[y].size)

            val column = input.map { it[x] }
            val top = column.subList(0, y).reversed()
            val bottom = column.subList(y + 1, column.size)

            scores.add(score(top, v) * score(left, v) * score(bottom, v) * score(right, v))
        }
    }
    return scores.max()
}