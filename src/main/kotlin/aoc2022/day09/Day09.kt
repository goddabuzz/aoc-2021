package aoc2022.day09

import readInput
import result
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {

    val testInput = readInput(2022, 9, true)
    result(part1(testInput), 13)
    result(part2(testInput), 1)

    val input = readInput(2022, 9)
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int = followPath(parseInput(input),2)

fun part2(input: List<String>): Int = followPath(parseInput(input),10)

private fun followPath(headPath: String, knots: Int): Int {
    val rope = Array(knots) { Point() }
    val tailVisits = mutableSetOf(Point())

    headPath.forEach { direction ->
        rope[0] = rope[0].move(direction)
        rope.indices.windowed(2, 1) { (head, tail) ->
            if (!rope[head].touches(rope[tail])) {
                rope[tail] = rope[tail].moveTowards(rope[head])
            }
        }
        tailVisits += rope.last()
    }
    return tailVisits.size
}

private data class Point(val x: Int = 0, val y: Int = 0) {
    fun move(direction: Char): Point =
        when (direction) {
            'U' -> copy(y = y - 1)
            'D' -> copy(y = y + 1)
            'L' -> copy(x = x - 1)
            'R' -> copy(x = x + 1)
            else -> throw IllegalArgumentException("Unknown Direction: $direction")
        }

    fun moveTowards(other: Point): Point =
        Point(
            (other.x - x).sign + x,
            (other.y - y).sign + y
        )

    fun touches(other: Point): Boolean =
        (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1

}

private fun parseInput(input: List<String>): String =
    input.joinToString("") { row ->
        val direction = row.substringBefore(" ")
        val numberOfMoves = row.substringAfter(' ').toInt()
        direction.repeat(numberOfMoves)
    }