package aoc2022.day02

import readInput
import result

fun main() {
    val t1 = readInput(2022, "day02/Day02_test")
    val p1 = readInput(2022, "day02/Day02")

    result(part1(t1), 15)
    result(part1(p1), 17189)
    result(part2(t1), 12)
    result(part2(p1), 13490)
}

fun part1(lines: List<String>): Int {
    return lines.map { it.split(" ") }.sumOf(::match1)
}
fun match1(input: List<String>): Int {
    val win = listOf(listOf("A", "Y"), listOf("B", "Z"), listOf("C", "X"))
    val draw = listOf(listOf("A", "X"), listOf("B", "Y"), listOf("C", "Z"))

    var score = when (input[1]) {
        Play.ROCK.player -> Play.ROCK.weight
        Play.PAPER.player -> Play.PAPER.weight
        else -> {
            Play.SCISSORS.weight
        }
    }

    if (win.any { it == input }) score += 6
    if (draw.any { it == input }) score += 3

    return score
}

fun part2(lines: List<String>): Int {

    val draw = mapOf("A" to 1, "B" to 2, "C" to 3)
    val loss = mapOf("C" to 2, "A" to 3, "B" to 1)
    val win = mapOf("C" to 1, "A" to 2, "B" to 3)

    return lines.map { it.split(" ") }.sumOf { (x, y) ->
        when (y) {
            Play.ROCK.player -> 0 + loss[x]!!
            Play.PAPER.player -> 3 + draw[x]!!
            else -> 6 + win[x]!!
        }
    }
}

enum class Play(val opponent: String, val player: String, val weight: Int) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3)
}