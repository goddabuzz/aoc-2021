package aoc2022.day01

import readInput
import result

fun main() {
    val t1 = readInput(2022, 1, true)
    val p1 = readInput(2022, 1);

    result(part1(chunk(t1)), 24000)
    result(part2(chunk(t1)), 45000)

    println(part1(chunk(p1)))
    println(part2(chunk(p1)))
}

fun part2(elfs: List<List<Int>>): Int = elfs.map { it.sumOf { i: Int -> i } }.sorted().takeLast(3).sum()

fun part1(elfs: List<List<Int>>): Int = elfs.maxOf { ints: List<Int> -> ints.sumOf { i: Int -> i } }

fun chunk(lines: List<String>): List<List<Int>> =
    lines.fold(mutableListOf(mutableListOf<Int>())) { chunks, items ->
        if (items.isBlank()) {
            chunks.add(mutableListOf())
        } else {
            chunks.last().add(items.toInt())
        }
        chunks
    }