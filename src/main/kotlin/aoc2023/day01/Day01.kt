package aoc2023.day01

import readInput
import result

val listOfNumbers = arrayOf("zeroissh", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

fun main() {

    val testInput = readInput(2023,1, true)
    result(part1(testInput), 142)
    result(part2(
        listOf("two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
            "7pqrstsixteen")
    ), 281)

    val input = readInput(2023, 1)
    //println(part1(input))
    println(part2(input))
    //println(solve(input, extendedDigits))
}

fun part1(input: List<String>): Int {
    return input.sumOf {
        val x = "${it.first { c -> c.isDigit() }}${it.last { c -> c.isDigit() }}".toInt()
        println("$x -> $it");
        x
    }
}
fun part2(input: List<String>): Int {

    return part1(input.map {
        // replace the first word with the index of listOfNumbers in the line
        line ->
            var x = line;
            while (listOfNumbers.any { x.contains(it) }) {
                var index = 0;
                while (index < x.length) {
                    for (i in 1..9) {
                        if (x.substring(index).startsWith(listOfNumbers[i])) {
                            x = x.replaceFirst(listOfNumbers[i], i.toString())
                            println(x)
                            index = 0
                            break;
                        }
                    }
                    index++
                }
            }
        x
    })
}

private fun solve(lines: List<String>, values: Collection<IndexedValue<String>>): Int = lines.sumOf { line ->
    val subset = values.filter { it.value in line }
    if (subset.isNotEmpty()) {
        10 * subset.minBy { line.indexOf(it.value) }.index + subset.maxBy { line.lastIndexOf(it.value) }.index
    } else {
        0
    }
}

private val digits = List(10) { IndexedValue(it, it.toString()) }
private val extendedDigits = digits + listOf(
    IndexedValue(1, "one"),
    IndexedValue(2, "two"),
    IndexedValue(3, "three"),
    IndexedValue(4, "four"),
    IndexedValue(5, "five"),
    IndexedValue(6, "six"),
    IndexedValue(7, "seven"),
    IndexedValue(8, "eight"),
    IndexedValue(9, "nine"),
)