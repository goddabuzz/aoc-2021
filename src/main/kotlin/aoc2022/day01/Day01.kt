package aoc2022.day01

import readInput
import result

fun main() {
    val t1 = readInput(2022,"day01/Day01_test")
    val p1 = readInput(2022,"day01/Day01");

    result(part1(splitElfs(t1)), 24000)
    result(part1(splitElfs(p1)), 67658)
    result(part2(splitElfs(t1)), 45000)
    result(part2(splitElfs(p1)), 45000)
}

fun part2(elfs: List<List<Int>>): Int {
    val elfCalorie = elfs.map { it.sumOf { i: Int -> i } }
    return elfCalorie.sorted().takeLast(3).sum()
}

fun part1(elfs: List<List<Int>>): Int {
    return elfs.maxOf { ints: List<Int> -> ints.sumOf { i: Int -> i } }
}

fun splitElfs(p1: List<String>): List<List<Int>> {
    val elfs = mutableListOf<List<Int>>()
    var elf = mutableListOf<Int>()
    elfs.add(elf)
    p1.forEach { s ->
        run {
            if (s.isEmpty()) {
                elf = mutableListOf()
                elfs.add(elf)
            } else {
                elf.add(s.toInt())
            }
        }
    }
    return elfs.toList()
}