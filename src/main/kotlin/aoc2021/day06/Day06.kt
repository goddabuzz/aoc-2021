package aoc2021.day06

import readInput
import result
import resultLong

fun main() {

    // exponenially
    // each fish creates a new fish every 7 days
    // each fish own number - days until it creates a new laternfish
    // each new fish 2 more days for a cycle

    // Example
    // fish = 3
    // 1: fish -> 2
    // 2: fish -> 1
    // 3: fish -> 0
    // 4: fish -> 6, 8
    // 5: fish -> 5, 7
    // 6: fish -> 4, 6

    fun part1(input: List<String>, duration: Int): Int {
        val fishes = input[0].split(",").filter { it.isNotBlank() }.map { it.toInt() }
        var days = mutableListOf<List<Int>>()
        days.add(fishes)

        for (i in 1..duration) {
            val newDays = mutableListOf<List<Int>>()
            val fishesToAdd = mutableListOf<Int>()

            days.onEach { fish ->
                newDays.add(fish.map {
                    if (it == 0) {
                        fishesToAdd.add(8)
                        6
                    } else
                    it - 1
                })
            }
            newDays.add(fishesToAdd)
            days = newDays
        }
        return days.sumOf { it.size }
    }

    fun fish(count: Long?): Long {
        return count ?: 0
    }

    fun part2(input: List<String>, duration: Int): Long {
        val fishes = input[0].split(",").filter { it.isNotBlank() }.map { it.toInt() }
        var grouped = fishes.groupBy { it }.mapValues {it.value.size.toLong() }

        for (i in 1..duration) {
            grouped = mapOf(
                0 to fish(grouped[1]),
                1 to fish(grouped[2]),
                2 to fish(grouped[3]),
                3 to fish(grouped[4]),
                4 to fish(grouped[5]),
                5 to fish(grouped[6]),
                6 to fish(grouped[7]) + fish(grouped[0]),
                7 to fish(grouped[8]),
                8 to fish(grouped[0])
            )
        }
        return grouped.toList().sumOf { it.second }
    }

    val testInput = readInput(2021, 6)
    result(part1(testInput, 80), 5934)
    resultLong(part2(testInput, 256), 26984457539)

    val input = readInput(2021, 6)
    result(part1(input, 80), 365131)
    resultLong(part2(input, 256), 1650309278600)
}