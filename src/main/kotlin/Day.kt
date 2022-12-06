fun main() {

    val testInput = readInput(2022,1, true).map { it.toInt() }
    result(part1(testInput), 0)
    result(part2(testInput), 0)

    val input = readInput(2022, 1).map { it.toInt() }
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<Int>): Int {
    return 0
}
fun part2(input: List<Int>): Int {
    return part1(input)
}