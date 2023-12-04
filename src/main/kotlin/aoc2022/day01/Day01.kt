package aoc2022.day01

import chunk
import readInput
import result

fun main() {
    val t1 = readInput(2022, 1, true)
    val p1 = readInput(2022, 1);

    result(part1(chunk(t1) { it.isBlank() }.map { it.map { s -> s.toInt() } }), 24000)
    result(part2(chunk(t1) { it.isBlank() }.map { it.map { s -> s.toInt() } }), 45000)

    println(part1(chunk(p1) { it.isBlank() }.map { it.map { s -> s.toInt() } }))
    println(part2(chunk(p1) { it.isBlank() }.map { it.map { s -> s.toInt() } }))
}

fun part2(elfs: List<List<Int>>): Int = elfs.map { it.sumOf { i: Int -> i } }.sorted().takeLast(3).sum()

fun part1(elfs: List<List<Int>>): Int = elfs.maxOf { ints: List<Int> -> ints.sumOf { i: Int -> i } }