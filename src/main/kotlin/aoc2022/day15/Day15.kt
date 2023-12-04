package aoc2022.day15

import readInput
import result

fun main() {

    val testInput = readInput(2022,15, true)
    result(part1(testInput), 26)
    //result(part2(testInput), 0)

    val input = readInput(2022, 15)
    println(part1(input))
    //println(part2(input))
}
data class Point(val x: Int, val y: Int)

fun part1(input: List<String>): Int {
    val points = input.map {i ->
        i.split(":").map {
            s -> s.split(",").map {
                c -> c.split(",").map {
                    p -> p.split("=")[1].toInt()
                }
            }
        }
    }
    return 0
}
fun part2(input: List<String>): Int {
    return part1(input)
}
