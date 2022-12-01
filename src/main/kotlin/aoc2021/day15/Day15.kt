package aoc2021.day15

import readInput
import result
import java.util.*

fun main() {
    // p1
    result(solve(readInput(2021, "Day15/Day15_test"), 1), 40)
    result(solve(readInput(2021, "Day15/Day15"), 1), 595)

    // p2
    result(solve(readInput(2021, "Day15/Day15_test"), 5), 315)
    result(solve(readInput(2021, "Day15/Day15"), 5), 595)
}

data class Position(val x: Int, val y: Int, var distance: Int) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        return if (distance > other.distance) 1 else -1
    }
}

fun solve(input: List<String>, factor: Int): Int {

    val map = input.map { it.split("").filter { s -> s.isNotEmpty() }.map { c -> c.toInt() } }

    val rows = map.size
    val cols = map[0].size

    val queue = PriorityQueue<Position>()
    val distanceMap = Array(rows*factor) { IntArray(cols * factor) { 0 } }
    val riskMap = Array(rows*factor) { IntArray(cols * factor) { 0 } }

    val x = listOf(-1, 0, 0, 1)
    val y = listOf(0, -1, 1, 0)

    distanceMap[0][0] = map[0][0]
    queue.add(Position(0, 0, map[0][0]))

    fun calculateRisk(dY: Int, dX: Int): Int {
        val originalValue = map[dY % rows][dX % cols]
        val add = (dY / rows) + (dX / cols)

        val risk = (originalValue + add) % 9
        return if (risk == 0) 9 else risk
    }

    while (queue.isNotEmpty()) {
        val pos = queue.poll()
        val distance = pos.distance

        for (i in y.indices) {
            val dX = pos.x + x[i]
            val dY = pos.y + y[i]

            if (dY == distanceMap.size - 1 && dX == distanceMap[0].size - 1) {
                return distance + calculateRisk(dY, dX) - map[0][0]
            }

            distanceMap.getOrNull(dY)?.getOrNull(dX)?.let {
                if (it == 0) {
                    val risk = calculateRisk(dY, dX)

                    if (pos.distance >= distanceMap[dY][dX] + risk) {
                        riskMap[dY][dX] = risk
                        distanceMap[dY][dX] = pos.distance + risk
                        queue.add(Position(dX, dY, distanceMap[dY][dX]))
                    }
                }
            }
        }
    }

    return 0
}
