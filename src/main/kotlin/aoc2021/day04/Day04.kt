package aoc2021.day04

import readInput
import result

fun main() {

    data class BingoField(var marked: Boolean, var value: Int)

    fun prepare(input: List<String>): List<List<List<BingoField>>> {
        return input.slice(1 until input.size)
            .filter { it.isNotEmpty() }
            .chunked(5)
            .map { card ->
            card.map { row ->
                row.split(" ").filter { it.isNotBlank() }
                    .map { n ->
                        BingoField(false, n.toInt())
                    }
            }
        }
    }

    fun part1(input: List<String>): Int {
        // Prepare bingo
        val numbers = input.slice(0..1)[0].split(",").map { it.toInt() }
        val cards = prepare(input)
        // Test
        numbers.forEach { number ->
            cards.forEach { card ->
                card.onEach { row ->
                    row.map {
                        it.marked = it.marked || number == it.value
                    }
                    // Row
                    if (row.all { pair -> pair.marked }) {
                        return number * card.sumOf {
                            it.filter { pair -> !pair.marked }.sumOf { p -> p.value }
                        }
                    }
                }
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        // Prepare bingo
        val numbers = input.slice(0..1)[0].split(",").map { it.toInt() }
        var cards = prepare(input)
        val done: MutableList<List<List<BingoField>>> = mutableListOf()
        // Test
        numbers.forEach { number ->
            cards.forEach { card ->
                card.onEach { row ->
                    row.map {
                        it.marked = it.marked || number == it.value
                    }
                }
            }

            var lastNumbers : List<BingoField> = emptyList()
            cards = cards.filter { !done.contains(it) }.onEach lit@{ card ->
                card.onEach { row ->
                    if (row.all { bingoField -> bingoField.marked }) {
                        lastNumbers = row
                        done.add(card)
                        return@lit
                    }
                    row.onEachIndexed { index, _ ->
                        val column = card.map { r -> r[index] }
                        if (column.all { it.marked }) {
                            lastNumbers = column
                            done.add(card)
                            return@lit
                        }
                    }
                }
            }

            if (cards.size == 1 && lastNumbers.isNotEmpty()) {
                return number * cards.first().sumOf {
                    it.filter { pair -> !pair.marked }.sumOf { p -> p.value }
                }
            }
        }

        return 0
    }

    val testInput = readInput(2021, 4)
    result(part1(testInput), 4512)
    result(part2(testInput), 1924)

    val input = readInput(2021, 4)
    result(part1(input), 44736)
    result(part2(input), 3379326)
}