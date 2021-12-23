package day15

import readInput
import result
import java.util.*

fun main() {

    result(part1(readInput("Day15/Day15_test")), 40)
    result(part1(readInput("Day15/Day15")), 40)
}
data class Position(val x: Int, val y: Int, var distance: Int) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        return if (distance > other.distance) 1 else -1
    }
}

fun part1(input: List<String>): Int {

    val map = input.map { it.split("").filter { s -> s.isNotEmpty() }.map { c -> c.toInt() } }

    val queue = PriorityQueue<Position>()
    val checked = Array(map.size) { IntArray(map[0].size) { 0 } }

    val x = listOf(-1, 0, 0, 1)
    val y = listOf(0, -1, 1, 0)

    checked[0][0] = map[0][0]
    queue.add(Position(0, 0, map[0][0]))

    while (queue.isNotEmpty()) {
        val pos = queue.poll()
        val distance = pos.distance

        for (i in y.indices) {
            val dX = pos.x + x[i]
            val dY = pos.y + y[i]

            if (dY == map.size - 1 && dX == map[0].size - 1) {
                return distance + map[dY][dX] - map[0][0]
            }

            map.getOrNull(dY)?.getOrNull(dX)?.let {
                if (checked[dY][dX] == 0) {
                    if (pos.distance >= checked[dY][dX] + it) {
                        checked[dY][dX] = pos.distance + it
                        queue.add(Position(dX, dY, pos.distance + it))
                    }
                }
            }
        }
    }

    return 0
}
