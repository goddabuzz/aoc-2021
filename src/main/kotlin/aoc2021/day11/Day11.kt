package aoc2021.day11

import readInput
import result

fun main() {

    result(part1(readInput(2021, "day11/Day11_test")), 1656)
    result(part1(readInput(2021, "day11/Day11")), 1591)
    result(part2(readInput(2021, "day11/Day11_test")), 195)
    result(part2(readInput(2021, "day11/Day11")), 314)
}

data class Octopus(val x: Int, val y: Int, var e: Int, var f: Boolean = false)

fun part1(readInput: List<String>): Int {

    var flashCounter = 0;

    // Map Octopus
    val levels = readInput
        .mapIndexed { y, r ->
            r.split("").filter { it.isNotEmpty() }
                .mapIndexed { x, v -> Octopus(x, y, v.toInt()) } }

    fun flash(octopus: Octopus) {
        octopus.f = true
        flashCounter++
        listOfNotNull(
            levels.getAt(octopus.x - 1, octopus.y - 1),
            levels.getAt(octopus.x, octopus.y - 1),
            levels.getAt(octopus.x + 1, octopus.y - 1),

            levels.getAt(octopus.x - 1, octopus.y),
            levels.getAt(octopus.x + 1, octopus.y),

            levels.getAt(octopus.x - 1, octopus.y + 1),
            levels.getAt(octopus.x, octopus.y + 1),
            levels.getAt(octopus.x + 1, octopus.y + 1)
        ).forEach {
            it.e++

            if (it.e > 9 && !it.f) {
                flash(it)
            }
        }
    }

    // Steps
    for (i in 0..99) {

        // First
        levels.onEach { r ->
            r.onEach {
                it.e += 1
            }
        }

        // Then
        levels.onEach { r ->
            r.onEach {
                if (it.e > 9 && !it.f) {
                    flash(it)
                }
            }
        }

        // Finally
        levels.onEach { r ->
            r.onEach {
                if (it.f) {
                    it.f = false
                    it.e = 0
                }
            }
        }
    }

    return flashCounter
}

fun part2(readInput: List<String>): Int {

    var flashCounter = 0;

    // Map Octopus
    val levels = readInput
        .mapIndexed { y, r ->
            r.split("").filter { it.isNotEmpty() }
                .mapIndexed { x, v -> Octopus(x, y, v.toInt()) } }

    fun flash(octopus: Octopus) {
        octopus.f = true
        flashCounter++
        listOfNotNull(
            levels.getAt(octopus.x - 1, octopus.y - 1),
            levels.getAt(octopus.x, octopus.y - 1),
            levels.getAt(octopus.x + 1, octopus.y - 1),

            levels.getAt(octopus.x - 1, octopus.y),
            levels.getAt(octopus.x + 1, octopus.y),

            levels.getAt(octopus.x - 1, octopus.y + 1),
            levels.getAt(octopus.x, octopus.y + 1),
            levels.getAt(octopus.x + 1, octopus.y + 1)
        ).forEach {
            it.e++

            if (it.e > 9 && !it.f) {
                flash(it)
            }
        }
    }

    // Steps
    var stepCounter = 0
    while (
        !levels.all { it.all { octopus -> octopus.e == levels[0][0].e } }
    ) {
        stepCounter ++
        if (stepCounter == 314) {
            levels.forEach {
                println(it.map { octopus -> octopus.e }.joinToString(""))
            }
        }

        // First
        levels.onEach { r ->
            r.onEach {
                it.e += 1
            }
        }

        // Then
        levels.onEach { r ->
            r.onEach {
                if (it.e > 9 && !it.f) {
                    flash(it)
                }
            }
        }

        // Finally
        levels.onEach { r ->
            r.onEach {
                if (it.f) {
                    it.f = false
                    it.e = 0
                }
            }
        }
    }

    return stepCounter
}

fun List<List<Octopus>>.getAt(x: Int, y: Int): Octopus? {
    return this.getOrNull(y)?.getOrNull(x)
}
