package day05

import readInput
import result

fun main() {

    data class Point(val x: Int, val y: Int) : Comparable<Point> {
        override fun compareTo(other: Point): Int {
            val xc = x.compareTo(other.x)
            if (xc == 0) {
                return y.compareTo(other.y)
            }
            return xc
        }
    }
    data class MapPoint(var x: Int, var y: Int, var value: Int = 0)

    fun calcSegments(input: List<String>): List<List<Point>> {
        return input
            .map { s -> s.split("->")
                .map { point ->
                    val (x, y) = point.trim().split(",").map { it.toInt() }
                    Point(x, y)
                }.sorted()
            }
    }

    fun createMap(mX: Int, mY: Int): MutableList<List<MapPoint>> {
        val map = mutableListOf<List<MapPoint>>()
        for (y in 0..mY) {
            val row = mutableListOf<MapPoint>()
            for (x in 0..mX) {
                row.add(MapPoint(x, y))
            }
            map.add(row)
        }
        return map
    }

    fun horizontalAndVertical(segments: List<List<Point>>): MutableList<List<MapPoint>> {

        val mX = segments.maxOf { list: List<Point> -> list.maxOf { point: Point -> point.x } }
        val mY = segments.maxOf { list: List<Point> -> list.maxOf { point: Point -> point.y } }

        val map = createMap(mX, mY)

        segments.filter { it[0].x == it[1].x || it[0].y == it[1].y }.forEach { segment ->
            for (y in segment[0].y..segment[1].y) {
                for (x in segment[0].x..segment[1].x) {
                    map[y][x].value++
                }
            }
        }

        return map
    }

    fun part1(input: List<String>): Int {
        val segments = calcSegments(input)
        val map = horizontalAndVertical(segments)
        map.forEach { println(it.map { c -> if (c.value == 0) "." else c.value }.joinToString(""))}
        return map.sumOf { it.count { p -> p.value >= 2 } }
    }

    fun part2(input: List<String>): Int {
        val segments = calcSegments(input)
        val map = horizontalAndVertical(segments)

        segments.filter { !(it[0].x == it[1].x || it[0].y == it[1].y) }.forEach { segment ->
            val seg = segment.sortedBy { point -> point.y }
            var x = seg[0].x
            val i = if (seg[0].x < seg[1].x) 1 else -1

            for (y in seg[0].y..seg[1].y) {
                map[y][x].value++
                x += i
            }
        }

        return map.sumOf { it.count { p -> p.value >= 2 } }
    }

    val testInput = readInput("day05/Day05_test")
    result(part1(testInput), 5)
    result(part2(testInput), 12)

    val input = readInput("day05/Day05")
    result(part1(input), 7436)
    result(part2(input), 21104)
}