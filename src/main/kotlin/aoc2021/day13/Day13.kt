package aoc2021.day13

import readInput
import result

fun main() {

    // Part 1 = 2
    result(part1(readInput(2021, 13, true)), 16)
    result(part1(readInput(2021, 13)), 95)
}
data class Point(val x: Int, val y: Int)

fun part1(input: List<String>): Int {
    val folds = input.filter { "fold along " in it}
        .map { it.substring(11).split("=") }

    val points = input.filter { "," in it }
        .map { it.split(",").let { (x, y) -> Point(x.toInt(), y.toInt()) } }

    var paper = arrayOfNulls<Array<String>>(points.maxOf { it.y } + 1)
        .map { arrayOfNulls<String>(points.maxOf { it.x } + 1) }

    points.forEach {
        paper[it.y][it.x] = "#"
    }

    folds.forEach {
        val sublist: List<Array<String?>>
        paper = if (it[0] == "y") {
            val y = it[1].toInt()
            sublist = paper.takeLast(paper.size - 1 - y).reversed()
            paper.subList(0, y)
        } else {
            val x = it[1].toInt()
            sublist = paper.map { row -> row.takeLast(row.size - 1 - x).reversed().toTypedArray() }
            paper.map { row -> row.copyOfRange(0, x) }
        }

        sublist.forEachIndexed { i, row ->
            row.forEachIndexed { j, c -> paper[i][j] = paper[i][j] ?: c }
        }

        // print
        paper.forEach { r ->
            println(r.joinToString("") { c -> c ?: "." })
        }
    }

    return paper.sumOf { r -> r.filterNotNull().count() }
}
