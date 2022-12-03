fun main() {

    val testInput = readInput(2022,"day01/Day01_test").map { it.toInt() }
    result(part1(testInput), 0)
    result(part2(testInput), 0)

    val input = readInput(2022, "day01/Day01").map { it.toInt() }
    result(part1(input), 0)
    result(part2(input), 0)
}

fun part1(input: List<Int>): Int {
    return 0
}
fun part2(input: List<Int>): Int {
    return part1(input)
}