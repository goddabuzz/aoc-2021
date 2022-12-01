package aoc2021.day09

import readInput
import result

fun main() {

    result(part1(f(readInput(2021, "day09/Day09_test"))), 15)
    result(part2(f(readInput(2021, "day09/Day09_test"))), 1134)

    result(part1(f(readInput(2021, "day09/Day09"))), 423)
    result(part2(f(readInput(2021, "day09/Day09"))), 1198704)
}

fun f(input: List<String>) = input.map { it.split("").filter { p -> p.isNotEmpty() }.map { p -> p.toInt() } }

fun findLowerPoints(numbers: List<List<Int>>): MutableList<Pair<Int, Int>> {
    val listOfLowPoints = mutableListOf<Pair<Int, Int>>()
    numbers.forEachIndexed { y, r ->
        r.forEachIndexed { x, p ->
            if (p < listOfNotNull(
                    r.getOrNull(x - 1),
                    r.getOrNull(x + 1),
                    numbers.getOrNull(y - 1)?.getOrNull(x),
                    numbers.getOrNull(y + 1)?.getOrNull(x)
                ).minOrNull()!!
            ) {
                listOfLowPoints.add(Pair(x, y))
            }
        }
    }
    return listOfLowPoints;
}

fun part1(numbers: List<List<Int>>): Int = findLowerPoints(numbers).sumOf { (x, y) -> numbers[y][x] + 1 }

fun part2(numbers: List<List<Int>>): Int {

    val lowPoints = findLowerPoints(numbers)
    val basinCount: List<Int> = lowPoints.map { (x, y) ->
        val map = Array(numbers.size) { i ->
            BooleanArray(numbers[i].size) { false }
        }

        fun isExpanding(x: Int, y: Int, prev: Int) {
            numbers.getOrNull(y)?.getOrNull(x)?.let {
                if (it != 9 && it > prev) {
                    map[y][x] = true

                    isExpanding(x - 1, y, it)
                    isExpanding(x + 1, y, it)
                    isExpanding(x, y - 1, it)
                    isExpanding(x, y + 1, it)
                }
            }
        }

        isExpanding(x, y, numbers[y][x] - 1)
        map.sumOf { row -> row.count { it } }
    }

    return basinCount
        .sortedDescending()
        .take(3)
        .reduce { acc, v -> acc * v }

}